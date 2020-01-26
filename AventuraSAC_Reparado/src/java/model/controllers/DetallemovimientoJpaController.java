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
import model.entities.Detallemovimiento;
import model.entities.Movimientoalmacen;

/**
 *
 * @author CHELLI BONITA
 */
public class DetallemovimientoJpaController implements Serializable {

    public DetallemovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallemovimiento detallemovimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimientoalmacen idMovimiento = detallemovimiento.getIdMovimiento();
            if (idMovimiento != null) {
                idMovimiento = em.getReference(idMovimiento.getClass(), idMovimiento.getIdMovimiento());
                detallemovimiento.setIdMovimiento(idMovimiento);
            }
            em.persist(detallemovimiento);
            if (idMovimiento != null) {
                idMovimiento.getDetallemovimientoList().add(detallemovimiento);
                idMovimiento = em.merge(idMovimiento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallemovimiento detallemovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemovimiento persistentDetallemovimiento = em.find(Detallemovimiento.class, detallemovimiento.getIdDetalle());
            Movimientoalmacen idMovimientoOld = persistentDetallemovimiento.getIdMovimiento();
            Movimientoalmacen idMovimientoNew = detallemovimiento.getIdMovimiento();
            if (idMovimientoNew != null) {
                idMovimientoNew = em.getReference(idMovimientoNew.getClass(), idMovimientoNew.getIdMovimiento());
                detallemovimiento.setIdMovimiento(idMovimientoNew);
            }
            detallemovimiento = em.merge(detallemovimiento);
            if (idMovimientoOld != null && !idMovimientoOld.equals(idMovimientoNew)) {
                idMovimientoOld.getDetallemovimientoList().remove(detallemovimiento);
                idMovimientoOld = em.merge(idMovimientoOld);
            }
            if (idMovimientoNew != null && !idMovimientoNew.equals(idMovimientoOld)) {
                idMovimientoNew.getDetallemovimientoList().add(detallemovimiento);
                idMovimientoNew = em.merge(idMovimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallemovimiento.getIdDetalle();
                if (findDetallemovimiento(id) == null) {
                    throw new NonexistentEntityException("The detallemovimiento with id " + id + " no longer exists.");
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
            Detallemovimiento detallemovimiento;
            try {
                detallemovimiento = em.getReference(Detallemovimiento.class, id);
                detallemovimiento.getIdDetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallemovimiento with id " + id + " no longer exists.", enfe);
            }
            Movimientoalmacen idMovimiento = detallemovimiento.getIdMovimiento();
            if (idMovimiento != null) {
                idMovimiento.getDetallemovimientoList().remove(detallemovimiento);
                idMovimiento = em.merge(idMovimiento);
            }
            em.remove(detallemovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallemovimiento> findDetallemovimientoEntities() {
        return findDetallemovimientoEntities(true, -1, -1);
    }

    public List<Detallemovimiento> findDetallemovimientoEntities(int maxResults, int firstResult) {
        return findDetallemovimientoEntities(false, maxResults, firstResult);
    }

    private List<Detallemovimiento> findDetallemovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallemovimiento.class));
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

    public Detallemovimiento findDetallemovimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallemovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallemovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallemovimiento> rt = cq.from(Detallemovimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
