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
import model.entities.Tipomodelo;

/**
 *
 * @author CHELLI BONITA
 */
public class TipomodeloJpaController implements Serializable {

    public TipomodeloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipomodelo tipomodelo) {
        if (tipomodelo.getFichatecnicaList() == null) {
            tipomodelo.setFichatecnicaList(new ArrayList<Fichatecnica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Fichatecnica> attachedFichatecnicaList = new ArrayList<Fichatecnica>();
            for (Fichatecnica fichatecnicaListFichatecnicaToAttach : tipomodelo.getFichatecnicaList()) {
                fichatecnicaListFichatecnicaToAttach = em.getReference(fichatecnicaListFichatecnicaToAttach.getClass(), fichatecnicaListFichatecnicaToAttach.getIdFicha());
                attachedFichatecnicaList.add(fichatecnicaListFichatecnicaToAttach);
            }
            tipomodelo.setFichatecnicaList(attachedFichatecnicaList);
            em.persist(tipomodelo);
            for (Fichatecnica fichatecnicaListFichatecnica : tipomodelo.getFichatecnicaList()) {
                Tipomodelo oldIdTipoModeloOfFichatecnicaListFichatecnica = fichatecnicaListFichatecnica.getIdTipoModelo();
                fichatecnicaListFichatecnica.setIdTipoModelo(tipomodelo);
                fichatecnicaListFichatecnica = em.merge(fichatecnicaListFichatecnica);
                if (oldIdTipoModeloOfFichatecnicaListFichatecnica != null) {
                    oldIdTipoModeloOfFichatecnicaListFichatecnica.getFichatecnicaList().remove(fichatecnicaListFichatecnica);
                    oldIdTipoModeloOfFichatecnicaListFichatecnica = em.merge(oldIdTipoModeloOfFichatecnicaListFichatecnica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipomodelo tipomodelo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipomodelo persistentTipomodelo = em.find(Tipomodelo.class, tipomodelo.getIdTipoModelo());
            List<Fichatecnica> fichatecnicaListOld = persistentTipomodelo.getFichatecnicaList();
            List<Fichatecnica> fichatecnicaListNew = tipomodelo.getFichatecnicaList();
            List<Fichatecnica> attachedFichatecnicaListNew = new ArrayList<Fichatecnica>();
            for (Fichatecnica fichatecnicaListNewFichatecnicaToAttach : fichatecnicaListNew) {
                fichatecnicaListNewFichatecnicaToAttach = em.getReference(fichatecnicaListNewFichatecnicaToAttach.getClass(), fichatecnicaListNewFichatecnicaToAttach.getIdFicha());
                attachedFichatecnicaListNew.add(fichatecnicaListNewFichatecnicaToAttach);
            }
            fichatecnicaListNew = attachedFichatecnicaListNew;
            tipomodelo.setFichatecnicaList(fichatecnicaListNew);
            tipomodelo = em.merge(tipomodelo);
            for (Fichatecnica fichatecnicaListOldFichatecnica : fichatecnicaListOld) {
                if (!fichatecnicaListNew.contains(fichatecnicaListOldFichatecnica)) {
                    fichatecnicaListOldFichatecnica.setIdTipoModelo(null);
                    fichatecnicaListOldFichatecnica = em.merge(fichatecnicaListOldFichatecnica);
                }
            }
            for (Fichatecnica fichatecnicaListNewFichatecnica : fichatecnicaListNew) {
                if (!fichatecnicaListOld.contains(fichatecnicaListNewFichatecnica)) {
                    Tipomodelo oldIdTipoModeloOfFichatecnicaListNewFichatecnica = fichatecnicaListNewFichatecnica.getIdTipoModelo();
                    fichatecnicaListNewFichatecnica.setIdTipoModelo(tipomodelo);
                    fichatecnicaListNewFichatecnica = em.merge(fichatecnicaListNewFichatecnica);
                    if (oldIdTipoModeloOfFichatecnicaListNewFichatecnica != null && !oldIdTipoModeloOfFichatecnicaListNewFichatecnica.equals(tipomodelo)) {
                        oldIdTipoModeloOfFichatecnicaListNewFichatecnica.getFichatecnicaList().remove(fichatecnicaListNewFichatecnica);
                        oldIdTipoModeloOfFichatecnicaListNewFichatecnica = em.merge(oldIdTipoModeloOfFichatecnicaListNewFichatecnica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipomodelo.getIdTipoModelo();
                if (findTipomodelo(id) == null) {
                    throw new NonexistentEntityException("The tipomodelo with id " + id + " no longer exists.");
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
            Tipomodelo tipomodelo;
            try {
                tipomodelo = em.getReference(Tipomodelo.class, id);
                tipomodelo.getIdTipoModelo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipomodelo with id " + id + " no longer exists.", enfe);
            }
            List<Fichatecnica> fichatecnicaList = tipomodelo.getFichatecnicaList();
            for (Fichatecnica fichatecnicaListFichatecnica : fichatecnicaList) {
                fichatecnicaListFichatecnica.setIdTipoModelo(null);
                fichatecnicaListFichatecnica = em.merge(fichatecnicaListFichatecnica);
            }
            em.remove(tipomodelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipomodelo> findTipomodeloEntities() {
        return findTipomodeloEntities(true, -1, -1);
    }

    public List<Tipomodelo> findTipomodeloEntities(int maxResults, int firstResult) {
        return findTipomodeloEntities(false, maxResults, firstResult);
    }

    private List<Tipomodelo> findTipomodeloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipomodelo.class));
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

    public Tipomodelo findTipomodelo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipomodelo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipomodeloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipomodelo> rt = cq.from(Tipomodelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
