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
import model.entities.Fichatecnica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Talla;

/**
 *
 * @author CHELLI BONITA
 */
public class TallaJpaController implements Serializable {

    public TallaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Talla talla) {
        if (talla.getFichatecnicaList() == null) {
            talla.setFichatecnicaList(new ArrayList<Fichatecnica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Fichatecnica> attachedFichatecnicaList = new ArrayList<Fichatecnica>();
            for (Fichatecnica fichatecnicaListFichatecnicaToAttach : talla.getFichatecnicaList()) {
                fichatecnicaListFichatecnicaToAttach = em.getReference(fichatecnicaListFichatecnicaToAttach.getClass(), fichatecnicaListFichatecnicaToAttach.getIdFicha());
                attachedFichatecnicaList.add(fichatecnicaListFichatecnicaToAttach);
            }
            talla.setFichatecnicaList(attachedFichatecnicaList);
            em.persist(talla);
            for (Fichatecnica fichatecnicaListFichatecnica : talla.getFichatecnicaList()) {
                Talla oldIdTallaOfFichatecnicaListFichatecnica = fichatecnicaListFichatecnica.getIdTalla();
                fichatecnicaListFichatecnica.setIdTalla(talla);
                fichatecnicaListFichatecnica = em.merge(fichatecnicaListFichatecnica);
                if (oldIdTallaOfFichatecnicaListFichatecnica != null) {
                    oldIdTallaOfFichatecnicaListFichatecnica.getFichatecnicaList().remove(fichatecnicaListFichatecnica);
                    oldIdTallaOfFichatecnicaListFichatecnica = em.merge(oldIdTallaOfFichatecnicaListFichatecnica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Talla talla) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Talla persistentTalla = em.find(Talla.class, talla.getIdTalla());
            List<Fichatecnica> fichatecnicaListOld = persistentTalla.getFichatecnicaList();
            List<Fichatecnica> fichatecnicaListNew = talla.getFichatecnicaList();
            List<Fichatecnica> attachedFichatecnicaListNew = new ArrayList<Fichatecnica>();
            for (Fichatecnica fichatecnicaListNewFichatecnicaToAttach : fichatecnicaListNew) {
                fichatecnicaListNewFichatecnicaToAttach = em.getReference(fichatecnicaListNewFichatecnicaToAttach.getClass(), fichatecnicaListNewFichatecnicaToAttach.getIdFicha());
                attachedFichatecnicaListNew.add(fichatecnicaListNewFichatecnicaToAttach);
            }
            fichatecnicaListNew = attachedFichatecnicaListNew;
            talla.setFichatecnicaList(fichatecnicaListNew);
            talla = em.merge(talla);
            for (Fichatecnica fichatecnicaListOldFichatecnica : fichatecnicaListOld) {
                if (!fichatecnicaListNew.contains(fichatecnicaListOldFichatecnica)) {
                    fichatecnicaListOldFichatecnica.setIdTalla(null);
                    fichatecnicaListOldFichatecnica = em.merge(fichatecnicaListOldFichatecnica);
                }
            }
            for (Fichatecnica fichatecnicaListNewFichatecnica : fichatecnicaListNew) {
                if (!fichatecnicaListOld.contains(fichatecnicaListNewFichatecnica)) {
                    Talla oldIdTallaOfFichatecnicaListNewFichatecnica = fichatecnicaListNewFichatecnica.getIdTalla();
                    fichatecnicaListNewFichatecnica.setIdTalla(talla);
                    fichatecnicaListNewFichatecnica = em.merge(fichatecnicaListNewFichatecnica);
                    if (oldIdTallaOfFichatecnicaListNewFichatecnica != null && !oldIdTallaOfFichatecnicaListNewFichatecnica.equals(talla)) {
                        oldIdTallaOfFichatecnicaListNewFichatecnica.getFichatecnicaList().remove(fichatecnicaListNewFichatecnica);
                        oldIdTallaOfFichatecnicaListNewFichatecnica = em.merge(oldIdTallaOfFichatecnicaListNewFichatecnica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = talla.getIdTalla();
                if (findTalla(id) == null) {
                    throw new NonexistentEntityException("The talla with id " + id + " no longer exists.");
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
            Talla talla;
            try {
                talla = em.getReference(Talla.class, id);
                talla.getIdTalla();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The talla with id " + id + " no longer exists.", enfe);
            }
            List<Fichatecnica> fichatecnicaList = talla.getFichatecnicaList();
            for (Fichatecnica fichatecnicaListFichatecnica : fichatecnicaList) {
                fichatecnicaListFichatecnica.setIdTalla(null);
                fichatecnicaListFichatecnica = em.merge(fichatecnicaListFichatecnica);
            }
            em.remove(talla);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Talla> findTallaEntities() {
        return findTallaEntities(true, -1, -1);
    }

    public List<Talla> findTallaEntities(int maxResults, int firstResult) {
        return findTallaEntities(false, maxResults, firstResult);
    }

    private List<Talla> findTallaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Talla.class));
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

    public Talla findTalla(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Talla.class, id);
        } finally {
            em.close();
        }
    }

    public int getTallaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Talla> rt = cq.from(Talla.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
