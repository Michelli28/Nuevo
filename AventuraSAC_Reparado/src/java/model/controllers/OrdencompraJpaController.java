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
import model.entities.Proveedor;
import model.entities.Empleado;
import model.entities.Movimientoalmacen;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Ordencompra;
import model.entities.OrdencompraDetalle;

/**
 *
 * @author CHELLI BONITA
 */
public class OrdencompraJpaController implements Serializable {

    public OrdencompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ordencompra ordencompra) {
        if (ordencompra.getMovimientoalmacenList() == null) {
            ordencompra.setMovimientoalmacenList(new ArrayList<Movimientoalmacen>());
        }
        if (ordencompra.getOrdencompraDetalleList() == null) {
            ordencompra.setOrdencompraDetalleList(new ArrayList<OrdencompraDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor idProveedor = ordencompra.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                ordencompra.setIdProveedor(idProveedor);
            }
            Empleado idEmpleado = ordencompra.getIdEmpleado();
            if (idEmpleado != null) {
                idEmpleado = em.getReference(idEmpleado.getClass(), idEmpleado.getIdEmpleado());
                ordencompra.setIdEmpleado(idEmpleado);
            }
            List<Movimientoalmacen> attachedMovimientoalmacenList = new ArrayList<Movimientoalmacen>();
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacenToAttach : ordencompra.getMovimientoalmacenList()) {
                movimientoalmacenListMovimientoalmacenToAttach = em.getReference(movimientoalmacenListMovimientoalmacenToAttach.getClass(), movimientoalmacenListMovimientoalmacenToAttach.getIdMovimiento());
                attachedMovimientoalmacenList.add(movimientoalmacenListMovimientoalmacenToAttach);
            }
            ordencompra.setMovimientoalmacenList(attachedMovimientoalmacenList);

            /*List<OrdencompraDetalle> attachedOrdencompraDetalleList = new ArrayList<OrdencompraDetalle>();
            for (OrdencompraDetalle ordencompraDetalleListOrdencompraDetalleToAttach : ordencompra.getOrdencompraDetalleList()) {
                ordencompraDetalleListOrdencompraDetalleToAttach = em.getReference(ordencompraDetalleListOrdencompraDetalleToAttach.getClass(), ordencompraDetalleListOrdencompraDetalleToAttach.getItem());
                attachedOrdencompraDetalleList.add(ordencompraDetalleListOrdencompraDetalleToAttach);
            }
            ordencompra.setOrdencompraDetalleList(attachedOrdencompraDetalleList);*/
            em.persist(ordencompra);
            if (idProveedor != null) {
                idProveedor.getOrdencompraList().add(ordencompra);
                idProveedor = em.merge(idProveedor);
            }
            if (idEmpleado != null) {
                idEmpleado.getOrdencompraList().add(ordencompra);
                idEmpleado = em.merge(idEmpleado);
            }
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacen : ordencompra.getMovimientoalmacenList()) {
                Ordencompra oldIdOrdenCompraOfMovimientoalmacenListMovimientoalmacen = movimientoalmacenListMovimientoalmacen.getIdOrdenCompra();
                movimientoalmacenListMovimientoalmacen.setIdOrdenCompra(ordencompra);
                movimientoalmacenListMovimientoalmacen = em.merge(movimientoalmacenListMovimientoalmacen);
                if (oldIdOrdenCompraOfMovimientoalmacenListMovimientoalmacen != null) {
                    oldIdOrdenCompraOfMovimientoalmacenListMovimientoalmacen.getMovimientoalmacenList().remove(movimientoalmacenListMovimientoalmacen);
                    oldIdOrdenCompraOfMovimientoalmacenListMovimientoalmacen = em.merge(oldIdOrdenCompraOfMovimientoalmacenListMovimientoalmacen);
                }
            }
            if (ordencompra.getOrdencompraDetalleList() != null) {
                for (OrdencompraDetalle ordencompraDetalleListOrdencompraDetalle : ordencompra.getOrdencompraDetalleList()) {
                   // Ordencompra oldIdOrdenCompraOfOrdencompraDetalleListOrdencompraDetalle = ordencompraDetalleListOrdencompraDetalle.getIdOrdenCompra();
                    //ordencompraDetalleListOrdencompraDetalle.setIdOrdenCompra(ordencompra);
                    //ordencompraDetalleListOrdencompraDetalle = em.merge(ordencompraDetalleListOrdencompraDetalle);
                    if(ordencompraDetalleListOrdencompraDetalle.getItem() == 0){
                        em.persist(ordencompraDetalleListOrdencompraDetalle);
                    }
                    else
                        em.merge(ordencompraDetalleListOrdencompraDetalle);
                    /*if (oldIdOrdenCompraOfOrdencompraDetalleListOrdencompraDetalle != null) {
                        oldIdOrdenCompraOfOrdencompraDetalleListOrdencompraDetalle.getOrdencompraDetalleList().remove(ordencompraDetalleListOrdencompraDetalle);
                        oldIdOrdenCompraOfOrdencompraDetalleListOrdencompraDetalle = em.merge(oldIdOrdenCompraOfOrdencompraDetalleListOrdencompraDetalle);
                    }*/
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ordencompra ordencompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ordencompra persistentOrdencompra = em.find(Ordencompra.class, ordencompra.getIdOrdenCompra());
            Proveedor idProveedorOld = persistentOrdencompra.getIdProveedor();
            Proveedor idProveedorNew = ordencompra.getIdProveedor();
            Empleado idEmpleadoOld = persistentOrdencompra.getIdEmpleado();
            Empleado idEmpleadoNew = ordencompra.getIdEmpleado();
            List<Movimientoalmacen> movimientoalmacenListOld = persistentOrdencompra.getMovimientoalmacenList();
            List<Movimientoalmacen> movimientoalmacenListNew = ordencompra.getMovimientoalmacenList();
            List<OrdencompraDetalle> ordencompraDetalleListOld = persistentOrdencompra.getOrdencompraDetalleList();
            List<OrdencompraDetalle> ordencompraDetalleListNew = ordencompra.getOrdencompraDetalleList();
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                ordencompra.setIdProveedor(idProveedorNew);
            }
            if (idEmpleadoNew != null) {
                idEmpleadoNew = em.getReference(idEmpleadoNew.getClass(), idEmpleadoNew.getIdEmpleado());
                ordencompra.setIdEmpleado(idEmpleadoNew);
            }
            List<Movimientoalmacen> attachedMovimientoalmacenListNew = new ArrayList<Movimientoalmacen>();
            for (Movimientoalmacen movimientoalmacenListNewMovimientoalmacenToAttach : movimientoalmacenListNew) {
                movimientoalmacenListNewMovimientoalmacenToAttach = em.getReference(movimientoalmacenListNewMovimientoalmacenToAttach.getClass(), movimientoalmacenListNewMovimientoalmacenToAttach.getIdMovimiento());
                attachedMovimientoalmacenListNew.add(movimientoalmacenListNewMovimientoalmacenToAttach);
            }
            movimientoalmacenListNew = attachedMovimientoalmacenListNew;
            ordencompra.setMovimientoalmacenList(movimientoalmacenListNew);
            List<OrdencompraDetalle> attachedOrdencompraDetalleListNew = new ArrayList<OrdencompraDetalle>();
            for (OrdencompraDetalle ordencompraDetalleListNewOrdencompraDetalleToAttach : ordencompraDetalleListNew) {
                ordencompraDetalleListNewOrdencompraDetalleToAttach = em.getReference(ordencompraDetalleListNewOrdencompraDetalleToAttach.getClass(), ordencompraDetalleListNewOrdencompraDetalleToAttach.getItem());
                attachedOrdencompraDetalleListNew.add(ordencompraDetalleListNewOrdencompraDetalleToAttach);
            }
            ordencompraDetalleListNew = attachedOrdencompraDetalleListNew;
            ordencompra.setOrdencompraDetalleList(ordencompraDetalleListNew);
            ordencompra = em.merge(ordencompra);
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getOrdencompraList().remove(ordencompra);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getOrdencompraList().add(ordencompra);
                idProveedorNew = em.merge(idProveedorNew);
            }
            if (idEmpleadoOld != null && !idEmpleadoOld.equals(idEmpleadoNew)) {
                idEmpleadoOld.getOrdencompraList().remove(ordencompra);
                idEmpleadoOld = em.merge(idEmpleadoOld);
            }
            if (idEmpleadoNew != null && !idEmpleadoNew.equals(idEmpleadoOld)) {
                idEmpleadoNew.getOrdencompraList().add(ordencompra);
                idEmpleadoNew = em.merge(idEmpleadoNew);
            }
            for (Movimientoalmacen movimientoalmacenListOldMovimientoalmacen : movimientoalmacenListOld) {
                if (!movimientoalmacenListNew.contains(movimientoalmacenListOldMovimientoalmacen)) {
                    movimientoalmacenListOldMovimientoalmacen.setIdOrdenCompra(null);
                    movimientoalmacenListOldMovimientoalmacen = em.merge(movimientoalmacenListOldMovimientoalmacen);
                }
            }
            for (Movimientoalmacen movimientoalmacenListNewMovimientoalmacen : movimientoalmacenListNew) {
                if (!movimientoalmacenListOld.contains(movimientoalmacenListNewMovimientoalmacen)) {
                    Ordencompra oldIdOrdenCompraOfMovimientoalmacenListNewMovimientoalmacen = movimientoalmacenListNewMovimientoalmacen.getIdOrdenCompra();
                    movimientoalmacenListNewMovimientoalmacen.setIdOrdenCompra(ordencompra);
                    movimientoalmacenListNewMovimientoalmacen = em.merge(movimientoalmacenListNewMovimientoalmacen);
                    if (oldIdOrdenCompraOfMovimientoalmacenListNewMovimientoalmacen != null && !oldIdOrdenCompraOfMovimientoalmacenListNewMovimientoalmacen.equals(ordencompra)) {
                        oldIdOrdenCompraOfMovimientoalmacenListNewMovimientoalmacen.getMovimientoalmacenList().remove(movimientoalmacenListNewMovimientoalmacen);
                        oldIdOrdenCompraOfMovimientoalmacenListNewMovimientoalmacen = em.merge(oldIdOrdenCompraOfMovimientoalmacenListNewMovimientoalmacen);
                    }
                }
            }
            for (OrdencompraDetalle ordencompraDetalleListOldOrdencompraDetalle : ordencompraDetalleListOld) {
                if (!ordencompraDetalleListNew.contains(ordencompraDetalleListOldOrdencompraDetalle)) {
                    ordencompraDetalleListOldOrdencompraDetalle.setIdOrdenCompra(null);
                    ordencompraDetalleListOldOrdencompraDetalle = em.merge(ordencompraDetalleListOldOrdencompraDetalle);
                }
            }
            for (OrdencompraDetalle ordencompraDetalleListNewOrdencompraDetalle : ordencompraDetalleListNew) {
                if (!ordencompraDetalleListOld.contains(ordencompraDetalleListNewOrdencompraDetalle)) {
                    Ordencompra oldIdOrdenCompraOfOrdencompraDetalleListNewOrdencompraDetalle = ordencompraDetalleListNewOrdencompraDetalle.getIdOrdenCompra();
                    ordencompraDetalleListNewOrdencompraDetalle.setIdOrdenCompra(ordencompra);
                    ordencompraDetalleListNewOrdencompraDetalle = em.merge(ordencompraDetalleListNewOrdencompraDetalle);
                    if (oldIdOrdenCompraOfOrdencompraDetalleListNewOrdencompraDetalle != null && !oldIdOrdenCompraOfOrdencompraDetalleListNewOrdencompraDetalle.equals(ordencompra)) {
                        oldIdOrdenCompraOfOrdencompraDetalleListNewOrdencompraDetalle.getOrdencompraDetalleList().remove(ordencompraDetalleListNewOrdencompraDetalle);
                        oldIdOrdenCompraOfOrdencompraDetalleListNewOrdencompraDetalle = em.merge(oldIdOrdenCompraOfOrdencompraDetalleListNewOrdencompraDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ordencompra.getIdOrdenCompra();
                if (findOrdencompra(id) == null) {
                    throw new NonexistentEntityException("The ordencompra with id " + id + " no longer exists.");
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
            Ordencompra ordencompra;
            try {
                ordencompra = em.getReference(Ordencompra.class, id);
                ordencompra.getIdOrdenCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordencompra with id " + id + " no longer exists.", enfe);
            }
            Proveedor idProveedor = ordencompra.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getOrdencompraList().remove(ordencompra);
                idProveedor = em.merge(idProveedor);
            }
            Empleado idEmpleado = ordencompra.getIdEmpleado();
            if (idEmpleado != null) {
                idEmpleado.getOrdencompraList().remove(ordencompra);
                idEmpleado = em.merge(idEmpleado);
            }
            List<Movimientoalmacen> movimientoalmacenList = ordencompra.getMovimientoalmacenList();
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacen : movimientoalmacenList) {
                movimientoalmacenListMovimientoalmacen.setIdOrdenCompra(null);
                movimientoalmacenListMovimientoalmacen = em.merge(movimientoalmacenListMovimientoalmacen);
            }
            List<OrdencompraDetalle> ordencompraDetalleList = ordencompra.getOrdencompraDetalleList();
            for (OrdencompraDetalle ordencompraDetalleListOrdencompraDetalle : ordencompraDetalleList) {
                ordencompraDetalleListOrdencompraDetalle.setIdOrdenCompra(null);
                ordencompraDetalleListOrdencompraDetalle = em.merge(ordencompraDetalleListOrdencompraDetalle);
            }
            em.remove(ordencompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ordencompra> findOrdencompraEntities() {
        return findOrdencompraEntities(true, -1, -1);
    }

    public List<Ordencompra> findOrdencompraEntities(int maxResults, int firstResult) {
        return findOrdencompraEntities(false, maxResults, firstResult);
    }

    private List<Ordencompra> findOrdencompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ordencompra.class));
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

    public Ordencompra findOrdencompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ordencompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdencompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ordencompra> rt = cq.from(Ordencompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
