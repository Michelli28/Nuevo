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
import model.entities.Ordencompra;
import model.entities.OrdencompraDetalle;

/**
 *
 * @author Administrador
 */
public class OrdencompraDetalleJpaController implements Serializable {

    public OrdencompraDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdencompraDetalle ordencompraDetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ordencompra idOrdenCompra = ordencompraDetalle.getIdOrdenCompra();
            if (idOrdenCompra != null) {
                idOrdenCompra = em.getReference(idOrdenCompra.getClass(), idOrdenCompra.getIdOrdenCompra());
                ordencompraDetalle.setIdOrdenCompra(idOrdenCompra);
            }
            em.persist(ordencompraDetalle);
            if (idOrdenCompra != null) {
                idOrdenCompra.getOrdencompraDetalleList().add(ordencompraDetalle);
                idOrdenCompra = em.merge(idOrdenCompra);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdencompraDetalle ordencompraDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdencompraDetalle persistentOrdencompraDetalle = em.find(OrdencompraDetalle.class, ordencompraDetalle.getItem());
            Ordencompra idOrdenCompraOld = persistentOrdencompraDetalle.getIdOrdenCompra();
            Ordencompra idOrdenCompraNew = ordencompraDetalle.getIdOrdenCompra();
            if (idOrdenCompraNew != null) {
                idOrdenCompraNew = em.getReference(idOrdenCompraNew.getClass(), idOrdenCompraNew.getIdOrdenCompra());
                ordencompraDetalle.setIdOrdenCompra(idOrdenCompraNew);
            }
            ordencompraDetalle = em.merge(ordencompraDetalle);
            if (idOrdenCompraOld != null && !idOrdenCompraOld.equals(idOrdenCompraNew)) {
                idOrdenCompraOld.getOrdencompraDetalleList().remove(ordencompraDetalle);
                idOrdenCompraOld = em.merge(idOrdenCompraOld);
            }
            if (idOrdenCompraNew != null && !idOrdenCompraNew.equals(idOrdenCompraOld)) {
                idOrdenCompraNew.getOrdencompraDetalleList().add(ordencompraDetalle);
                idOrdenCompraNew = em.merge(idOrdenCompraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ordencompraDetalle.getItem();
                if (findOrdencompraDetalle(id) == null) {
                    throw new NonexistentEntityException("The ordencompraDetalle with id " + id + " no longer exists.");
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
            OrdencompraDetalle ordencompraDetalle;
            try {
                ordencompraDetalle = em.getReference(OrdencompraDetalle.class, id);
                ordencompraDetalle.getItem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordencompraDetalle with id " + id + " no longer exists.", enfe);
            }
            Ordencompra idOrdenCompra = ordencompraDetalle.getIdOrdenCompra();
            if (idOrdenCompra != null) {
                idOrdenCompra.getOrdencompraDetalleList().remove(ordencompraDetalle);
                idOrdenCompra = em.merge(idOrdenCompra);
            }
            em.remove(ordencompraDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdencompraDetalle> findOrdencompraDetalleEntities() {
        return findOrdencompraDetalleEntities(true, -1, -1);
    }

    public List<OrdencompraDetalle> findOrdencompraDetalleEntities(int maxResults, int firstResult) {
        return findOrdencompraDetalleEntities(false, maxResults, firstResult);
    }

    private List<OrdencompraDetalle> findOrdencompraDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdencompraDetalle.class));
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

    public OrdencompraDetalle findOrdencompraDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdencompraDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdencompraDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdencompraDetalle> rt = cq.from(OrdencompraDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
