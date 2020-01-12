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
import model.entities.Ordencompra;
import model.entities.Movimientoalmacen;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.controllers.exceptions.PreexistingEntityException;
import model.entities.OrdencompraDetalle;

/**
 *
 * @author CHELLI BONITA
 */
public class OrdencompraDetalleJpaController implements Serializable {

    public OrdencompraDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdencompraDetalle ordencompraDetalle) throws PreexistingEntityException, Exception {
        if (ordencompraDetalle.getMovimientoalmacenList() == null) {
            ordencompraDetalle.setMovimientoalmacenList(new ArrayList<Movimientoalmacen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ordencompra idOrdenCompra = ordencompraDetalle.getIdOrdenCompra();
            if (idOrdenCompra != null) {
                idOrdenCompra = em.getReference(idOrdenCompra.getClass(), idOrdenCompra.getIdOrdenCompra());
                ordencompraDetalle.setIdOrdenCompra(idOrdenCompra);
            }
            List<Movimientoalmacen> attachedMovimientoalmacenList = new ArrayList<Movimientoalmacen>();
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacenToAttach : ordencompraDetalle.getMovimientoalmacenList()) {
                movimientoalmacenListMovimientoalmacenToAttach = em.getReference(movimientoalmacenListMovimientoalmacenToAttach.getClass(), movimientoalmacenListMovimientoalmacenToAttach.getIdMovimiento());
                attachedMovimientoalmacenList.add(movimientoalmacenListMovimientoalmacenToAttach);
            }
            ordencompraDetalle.setMovimientoalmacenList(attachedMovimientoalmacenList);
            em.persist(ordencompraDetalle);
            if (idOrdenCompra != null) {
                idOrdenCompra.getOrdencompraDetalleList().add(ordencompraDetalle);
                idOrdenCompra = em.merge(idOrdenCompra);
            }
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacen : ordencompraDetalle.getMovimientoalmacenList()) {
                OrdencompraDetalle oldItemOfMovimientoalmacenListMovimientoalmacen = movimientoalmacenListMovimientoalmacen.getItem();
                movimientoalmacenListMovimientoalmacen.setItem(ordencompraDetalle);
                movimientoalmacenListMovimientoalmacen = em.merge(movimientoalmacenListMovimientoalmacen);
                if (oldItemOfMovimientoalmacenListMovimientoalmacen != null) {
                    oldItemOfMovimientoalmacenListMovimientoalmacen.getMovimientoalmacenList().remove(movimientoalmacenListMovimientoalmacen);
                    oldItemOfMovimientoalmacenListMovimientoalmacen = em.merge(oldItemOfMovimientoalmacenListMovimientoalmacen);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrdencompraDetalle(ordencompraDetalle.getItem()) != null) {
                throw new PreexistingEntityException("OrdencompraDetalle " + ordencompraDetalle + " already exists.", ex);
            }
            throw ex;
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
            List<Movimientoalmacen> movimientoalmacenListOld = persistentOrdencompraDetalle.getMovimientoalmacenList();
            List<Movimientoalmacen> movimientoalmacenListNew = ordencompraDetalle.getMovimientoalmacenList();
            if (idOrdenCompraNew != null) {
                idOrdenCompraNew = em.getReference(idOrdenCompraNew.getClass(), idOrdenCompraNew.getIdOrdenCompra());
                ordencompraDetalle.setIdOrdenCompra(idOrdenCompraNew);
            }
            List<Movimientoalmacen> attachedMovimientoalmacenListNew = new ArrayList<Movimientoalmacen>();
            for (Movimientoalmacen movimientoalmacenListNewMovimientoalmacenToAttach : movimientoalmacenListNew) {
                movimientoalmacenListNewMovimientoalmacenToAttach = em.getReference(movimientoalmacenListNewMovimientoalmacenToAttach.getClass(), movimientoalmacenListNewMovimientoalmacenToAttach.getIdMovimiento());
                attachedMovimientoalmacenListNew.add(movimientoalmacenListNewMovimientoalmacenToAttach);
            }
            movimientoalmacenListNew = attachedMovimientoalmacenListNew;
            ordencompraDetalle.setMovimientoalmacenList(movimientoalmacenListNew);
            ordencompraDetalle = em.merge(ordencompraDetalle);
            if (idOrdenCompraOld != null && !idOrdenCompraOld.equals(idOrdenCompraNew)) {
                idOrdenCompraOld.getOrdencompraDetalleList().remove(ordencompraDetalle);
                idOrdenCompraOld = em.merge(idOrdenCompraOld);
            }
            if (idOrdenCompraNew != null && !idOrdenCompraNew.equals(idOrdenCompraOld)) {
                idOrdenCompraNew.getOrdencompraDetalleList().add(ordencompraDetalle);
                idOrdenCompraNew = em.merge(idOrdenCompraNew);
            }
            for (Movimientoalmacen movimientoalmacenListOldMovimientoalmacen : movimientoalmacenListOld) {
                if (!movimientoalmacenListNew.contains(movimientoalmacenListOldMovimientoalmacen)) {
                    movimientoalmacenListOldMovimientoalmacen.setItem(null);
                    movimientoalmacenListOldMovimientoalmacen = em.merge(movimientoalmacenListOldMovimientoalmacen);
                }
            }
            for (Movimientoalmacen movimientoalmacenListNewMovimientoalmacen : movimientoalmacenListNew) {
                if (!movimientoalmacenListOld.contains(movimientoalmacenListNewMovimientoalmacen)) {
                    OrdencompraDetalle oldItemOfMovimientoalmacenListNewMovimientoalmacen = movimientoalmacenListNewMovimientoalmacen.getItem();
                    movimientoalmacenListNewMovimientoalmacen.setItem(ordencompraDetalle);
                    movimientoalmacenListNewMovimientoalmacen = em.merge(movimientoalmacenListNewMovimientoalmacen);
                    if (oldItemOfMovimientoalmacenListNewMovimientoalmacen != null && !oldItemOfMovimientoalmacenListNewMovimientoalmacen.equals(ordencompraDetalle)) {
                        oldItemOfMovimientoalmacenListNewMovimientoalmacen.getMovimientoalmacenList().remove(movimientoalmacenListNewMovimientoalmacen);
                        oldItemOfMovimientoalmacenListNewMovimientoalmacen = em.merge(oldItemOfMovimientoalmacenListNewMovimientoalmacen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ordencompraDetalle.getItem();
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

    public void destroy(String id) throws NonexistentEntityException {
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
            List<Movimientoalmacen> movimientoalmacenList = ordencompraDetalle.getMovimientoalmacenList();
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacen : movimientoalmacenList) {
                movimientoalmacenListMovimientoalmacen.setItem(null);
                movimientoalmacenListMovimientoalmacen = em.merge(movimientoalmacenListMovimientoalmacen);
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

    public OrdencompraDetalle findOrdencompraDetalle(String id) {
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
