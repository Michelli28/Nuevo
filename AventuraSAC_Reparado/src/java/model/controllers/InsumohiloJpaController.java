/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Insumohilo;

/**
 *
 * @author CHELLI BONITA
 */
public class InsumohiloJpaController implements Serializable {

    public InsumohiloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Insumohilo insumohilo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(insumohilo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Insumohilo insumohilo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            insumohilo = em.merge(insumohilo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = insumohilo.getIdInsumo();
                if (findInsumohilo(id) == null) {
                    throw new NonexistentEntityException("The insumohilo with id " + id + " no longer exists.");
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
            Insumohilo insumohilo;
            try {
                insumohilo = em.getReference(Insumohilo.class, id);
                insumohilo.getIdInsumo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insumohilo with id " + id + " no longer exists.", enfe);
            }
            em.remove(insumohilo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Insumohilo> findInsumohiloEntities() {
        return findInsumohiloEntities(true, -1, -1);
    }

    public List<Insumohilo> findInsumohiloEntities(int maxResults, int firstResult) {
        return findInsumohiloEntities(false, maxResults, firstResult);
    }

    private List<Insumohilo> findInsumohiloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Insumohilo.class));
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

    public Insumohilo findInsumohilo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Insumohilo.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsumohiloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Insumohilo> rt = cq.from(Insumohilo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
