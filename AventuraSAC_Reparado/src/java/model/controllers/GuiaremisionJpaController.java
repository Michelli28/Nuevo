/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entities.GuiaremisionDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Guiaremision;

/**
 *
 * @author CHELLI BONITA
 */
public class GuiaremisionJpaController implements Serializable {

    public GuiaremisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Guiaremision guiaremision) {
        if (guiaremision.getGuiaremisionDetalleList() == null) {
            guiaremision.setGuiaremisionDetalleList(new ArrayList<GuiaremisionDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<GuiaremisionDetalle> attachedGuiaremisionDetalleList = new ArrayList<GuiaremisionDetalle>();
            for (GuiaremisionDetalle guiaremisionDetalleListGuiaremisionDetalleToAttach : guiaremision.getGuiaremisionDetalleList()) {
                guiaremisionDetalleListGuiaremisionDetalleToAttach = em.getReference(guiaremisionDetalleListGuiaremisionDetalleToAttach.getClass(), guiaremisionDetalleListGuiaremisionDetalleToAttach.getIdDetalleGuiaRemision());
                attachedGuiaremisionDetalleList.add(guiaremisionDetalleListGuiaremisionDetalleToAttach);
            }
            guiaremision.setGuiaremisionDetalleList(attachedGuiaremisionDetalleList);
            em.persist(guiaremision);
            for (GuiaremisionDetalle guiaremisionDetalleListGuiaremisionDetalle : guiaremision.getGuiaremisionDetalleList()) {
                Guiaremision oldIdGuiaRemisionOfGuiaremisionDetalleListGuiaremisionDetalle = guiaremisionDetalleListGuiaremisionDetalle.getIdGuiaRemision();
                guiaremisionDetalleListGuiaremisionDetalle.setIdGuiaRemision(guiaremision);
                guiaremisionDetalleListGuiaremisionDetalle = em.merge(guiaremisionDetalleListGuiaremisionDetalle);
                if (oldIdGuiaRemisionOfGuiaremisionDetalleListGuiaremisionDetalle != null) {
                    oldIdGuiaRemisionOfGuiaremisionDetalleListGuiaremisionDetalle.getGuiaremisionDetalleList().remove(guiaremisionDetalleListGuiaremisionDetalle);
                    oldIdGuiaRemisionOfGuiaremisionDetalleListGuiaremisionDetalle = em.merge(oldIdGuiaRemisionOfGuiaremisionDetalleListGuiaremisionDetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Guiaremision guiaremision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Guiaremision persistentGuiaremision = em.find(Guiaremision.class, guiaremision.getIdGuiaRemision());
            List<GuiaremisionDetalle> guiaremisionDetalleListOld = persistentGuiaremision.getGuiaremisionDetalleList();
            List<GuiaremisionDetalle> guiaremisionDetalleListNew = guiaremision.getGuiaremisionDetalleList();
            List<GuiaremisionDetalle> attachedGuiaremisionDetalleListNew = new ArrayList<GuiaremisionDetalle>();
            for (GuiaremisionDetalle guiaremisionDetalleListNewGuiaremisionDetalleToAttach : guiaremisionDetalleListNew) {
                guiaremisionDetalleListNewGuiaremisionDetalleToAttach = em.getReference(guiaremisionDetalleListNewGuiaremisionDetalleToAttach.getClass(), guiaremisionDetalleListNewGuiaremisionDetalleToAttach.getIdDetalleGuiaRemision());
                attachedGuiaremisionDetalleListNew.add(guiaremisionDetalleListNewGuiaremisionDetalleToAttach);
            }
            guiaremisionDetalleListNew = attachedGuiaremisionDetalleListNew;
            guiaremision.setGuiaremisionDetalleList(guiaremisionDetalleListNew);
            guiaremision = em.merge(guiaremision);
            for (GuiaremisionDetalle guiaremisionDetalleListOldGuiaremisionDetalle : guiaremisionDetalleListOld) {
                if (!guiaremisionDetalleListNew.contains(guiaremisionDetalleListOldGuiaremisionDetalle)) {
                    guiaremisionDetalleListOldGuiaremisionDetalle.setIdGuiaRemision(null);
                    guiaremisionDetalleListOldGuiaremisionDetalle = em.merge(guiaremisionDetalleListOldGuiaremisionDetalle);
                }
            }
            for (GuiaremisionDetalle guiaremisionDetalleListNewGuiaremisionDetalle : guiaremisionDetalleListNew) {
                if (!guiaremisionDetalleListOld.contains(guiaremisionDetalleListNewGuiaremisionDetalle)) {
                    Guiaremision oldIdGuiaRemisionOfGuiaremisionDetalleListNewGuiaremisionDetalle = guiaremisionDetalleListNewGuiaremisionDetalle.getIdGuiaRemision();
                    guiaremisionDetalleListNewGuiaremisionDetalle.setIdGuiaRemision(guiaremision);
                    guiaremisionDetalleListNewGuiaremisionDetalle = em.merge(guiaremisionDetalleListNewGuiaremisionDetalle);
                    if (oldIdGuiaRemisionOfGuiaremisionDetalleListNewGuiaremisionDetalle != null && !oldIdGuiaRemisionOfGuiaremisionDetalleListNewGuiaremisionDetalle.equals(guiaremision)) {
                        oldIdGuiaRemisionOfGuiaremisionDetalleListNewGuiaremisionDetalle.getGuiaremisionDetalleList().remove(guiaremisionDetalleListNewGuiaremisionDetalle);
                        oldIdGuiaRemisionOfGuiaremisionDetalleListNewGuiaremisionDetalle = em.merge(oldIdGuiaRemisionOfGuiaremisionDetalleListNewGuiaremisionDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = guiaremision.getIdGuiaRemision();
                if (findGuiaremision(id) == null) {
                    throw new NonexistentEntityException("The guiaremision with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Guiaremision guiaremision;
            try {
                guiaremision = em.getReference(Guiaremision.class, id);
                guiaremision.getIdGuiaRemision();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The guiaremision with id " + id + " no longer exists.", enfe);
            }
            List<GuiaremisionDetalle> guiaremisionDetalleList = guiaremision.getGuiaremisionDetalleList();
            for (GuiaremisionDetalle guiaremisionDetalleListGuiaremisionDetalle : guiaremisionDetalleList) {
                guiaremisionDetalleListGuiaremisionDetalle.setIdGuiaRemision(null);
                guiaremisionDetalleListGuiaremisionDetalle = em.merge(guiaremisionDetalleListGuiaremisionDetalle);
            }
            em.remove(guiaremision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Guiaremision> findGuiaremisionEntities() {
        return findGuiaremisionEntities(true, -1, -1);
    }

    public List<Guiaremision> findGuiaremisionEntities(int maxResults, int firstResult) {
        return findGuiaremisionEntities(false, maxResults, firstResult);
    }

    private List<Guiaremision> findGuiaremisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Guiaremision.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Guiaremision findGuiaremision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Guiaremision.class, id);
        } finally {
            em.close();
        }
    }

    public int getGuiaremisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Guiaremision> rt = cq.from(Guiaremision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
