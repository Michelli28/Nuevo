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
import model.entities.Tipoitem;
import model.entities.Ordencompra;
import model.entities.Detallemovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Movimientoalmacen;

/**
 *
 * @author CHELLI BONITA
 */
public class MovimientoalmacenJpaController implements Serializable {

    public MovimientoalmacenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimientoalmacen movimientoalmacen) {
        if (movimientoalmacen.getDetallemovimientoList() == null) {
            movimientoalmacen.setDetallemovimientoList(new ArrayList<Detallemovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoitem idTipoItem = movimientoalmacen.getIdTipoItem();
            if (idTipoItem != null) {
                idTipoItem = em.getReference(idTipoItem.getClass(), idTipoItem.getIdTipoItem());
                movimientoalmacen.setIdTipoItem(idTipoItem);
            }
            Ordencompra idOrdenCompra = movimientoalmacen.getIdOrdenCompra();
            if (idOrdenCompra != null) {
                idOrdenCompra = em.getReference(idOrdenCompra.getClass(), idOrdenCompra.getIdOrdenCompra());
                movimientoalmacen.setIdOrdenCompra(idOrdenCompra);
            }
            List<Detallemovimiento> attachedDetallemovimientoList = new ArrayList<Detallemovimiento>();
            for (Detallemovimiento detallemovimientoListDetallemovimientoToAttach : movimientoalmacen.getDetallemovimientoList()) {
                if (detallemovimientoListDetallemovimientoToAttach.getIdDetalle() != null) {
                    //detallemovimientoListDetallemovimientoToAttach = em.getReference(detallemovimientoListDetallemovimientoToAttach.getClass(), detallemovimientoListDetallemovimientoToAttach.getIdDetalle());
                    attachedDetallemovimientoList.add(detallemovimientoListDetallemovimientoToAttach);
                }
            }
            movimientoalmacen.setDetallemovimientoList(attachedDetallemovimientoList);
            em.persist(movimientoalmacen);
            if (idTipoItem != null) {
                idTipoItem.getMovimientoalmacenList().add(movimientoalmacen);
                idTipoItem = em.merge(idTipoItem);
            }
            if (idOrdenCompra != null) {
                idOrdenCompra.getMovimientoalmacenList().add(movimientoalmacen);
                idOrdenCompra = em.merge(idOrdenCompra);
            }
            if (movimientoalmacen.getDetallemovimientoList() != null) {
                for (Detallemovimiento detallemovimientoListDetallemovimiento : movimientoalmacen.getDetallemovimientoList()) {
                   // Movimientoalmacen oldIdMovimientoOfDetallemovimientoListDetallemovimiento = detallemovimientoListDetallemovimiento.getIdMovimiento();
                    //detallemovimientoListDetallemovimiento.setIdMovimiento(movimientoalmacen);
                    //detallemovimientoListDetallemovimiento = em.merge(detallemovimientoListDetallemovimiento);
                    if(detallemovimientoListDetallemovimiento.getIdDetalle() == 0){
                         em.persist(detallemovimientoListDetallemovimiento);
                    } else {
                        em.merge(detallemovimientoListDetallemovimiento);
                    
                   // if (oldIdMovimientoOfDetallemovimientoListDetallemovimiento != null) {
                     //   oldIdMovimientoOfDetallemovimientoListDetallemovimiento.getDetallemovimientoList().remove(detallemovimientoListDetallemovimiento);
                       // oldIdMovimientoOfDetallemovimientoListDetallemovimiento = em.merge(oldIdMovimientoOfDetallemovimientoListDetallemovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimientoalmacen movimientoalmacen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimientoalmacen persistentMovimientoalmacen = em.find(Movimientoalmacen.class, movimientoalmacen.getIdMovimiento());
            Tipoitem idTipoItemOld = persistentMovimientoalmacen.getIdTipoItem();
            Tipoitem idTipoItemNew = movimientoalmacen.getIdTipoItem();
            Ordencompra idOrdenCompraOld = persistentMovimientoalmacen.getIdOrdenCompra();
            Ordencompra idOrdenCompraNew = movimientoalmacen.getIdOrdenCompra();
            List<Detallemovimiento> detallemovimientoListOld = persistentMovimientoalmacen.getDetallemovimientoList();
            List<Detallemovimiento> detallemovimientoListNew = movimientoalmacen.getDetallemovimientoList();
            if (idTipoItemNew != null) {
                idTipoItemNew = em.getReference(idTipoItemNew.getClass(), idTipoItemNew.getIdTipoItem());
                movimientoalmacen.setIdTipoItem(idTipoItemNew);
            }
            if (idOrdenCompraNew != null) {
                idOrdenCompraNew = em.getReference(idOrdenCompraNew.getClass(), idOrdenCompraNew.getIdOrdenCompra());
                movimientoalmacen.setIdOrdenCompra(idOrdenCompraNew);
            }
            List<Detallemovimiento> attachedDetallemovimientoListNew = new ArrayList<Detallemovimiento>();
            for (Detallemovimiento detallemovimientoListNewDetallemovimientoToAttach : detallemovimientoListNew) {
                detallemovimientoListNewDetallemovimientoToAttach = em.getReference(detallemovimientoListNewDetallemovimientoToAttach.getClass(), detallemovimientoListNewDetallemovimientoToAttach.getIdDetalle());
                attachedDetallemovimientoListNew.add(detallemovimientoListNewDetallemovimientoToAttach);
            }
            detallemovimientoListNew = attachedDetallemovimientoListNew;
            movimientoalmacen.setDetallemovimientoList(detallemovimientoListNew);
            movimientoalmacen = em.merge(movimientoalmacen);
            if (idTipoItemOld != null && !idTipoItemOld.equals(idTipoItemNew)) {
                idTipoItemOld.getMovimientoalmacenList().remove(movimientoalmacen);
                idTipoItemOld = em.merge(idTipoItemOld);
            }
            if (idTipoItemNew != null && !idTipoItemNew.equals(idTipoItemOld)) {
                idTipoItemNew.getMovimientoalmacenList().add(movimientoalmacen);
                idTipoItemNew = em.merge(idTipoItemNew);
            }
            if (idOrdenCompraOld != null && !idOrdenCompraOld.equals(idOrdenCompraNew)) {
                idOrdenCompraOld.getMovimientoalmacenList().remove(movimientoalmacen);
                idOrdenCompraOld = em.merge(idOrdenCompraOld);
            }
            if (idOrdenCompraNew != null && !idOrdenCompraNew.equals(idOrdenCompraOld)) {
                idOrdenCompraNew.getMovimientoalmacenList().add(movimientoalmacen);
                idOrdenCompraNew = em.merge(idOrdenCompraNew);
            }
            for (Detallemovimiento detallemovimientoListOldDetallemovimiento : detallemovimientoListOld) {
                if (!detallemovimientoListNew.contains(detallemovimientoListOldDetallemovimiento)) {
                    detallemovimientoListOldDetallemovimiento.setIdMovimiento(null);
                    detallemovimientoListOldDetallemovimiento = em.merge(detallemovimientoListOldDetallemovimiento);
                }
            }
            for (Detallemovimiento detallemovimientoListNewDetallemovimiento : detallemovimientoListNew) {
                if (!detallemovimientoListOld.contains(detallemovimientoListNewDetallemovimiento)) {
                    Movimientoalmacen oldIdMovimientoOfDetallemovimientoListNewDetallemovimiento = detallemovimientoListNewDetallemovimiento.getIdMovimiento();
                    detallemovimientoListNewDetallemovimiento.setIdMovimiento(movimientoalmacen);
                    detallemovimientoListNewDetallemovimiento = em.merge(detallemovimientoListNewDetallemovimiento);
                    if (oldIdMovimientoOfDetallemovimientoListNewDetallemovimiento != null && !oldIdMovimientoOfDetallemovimientoListNewDetallemovimiento.equals(movimientoalmacen)) {
                        oldIdMovimientoOfDetallemovimientoListNewDetallemovimiento.getDetallemovimientoList().remove(detallemovimientoListNewDetallemovimiento);
                        oldIdMovimientoOfDetallemovimientoListNewDetallemovimiento = em.merge(oldIdMovimientoOfDetallemovimientoListNewDetallemovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movimientoalmacen.getIdMovimiento();
                if (findMovimientoalmacen(id) == null) {
                    throw new NonexistentEntityException("The movimientoalmacen with id " + id + " no longer exists.");
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
            Movimientoalmacen movimientoalmacen;
            try {
                movimientoalmacen = em.getReference(Movimientoalmacen.class, id);
                movimientoalmacen.getIdMovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientoalmacen with id " + id + " no longer exists.", enfe);
            }
            Tipoitem idTipoItem = movimientoalmacen.getIdTipoItem();
            if (idTipoItem != null) {
                idTipoItem.getMovimientoalmacenList().remove(movimientoalmacen);
                idTipoItem = em.merge(idTipoItem);
            }
            Ordencompra idOrdenCompra = movimientoalmacen.getIdOrdenCompra();
            if (idOrdenCompra != null) {
                idOrdenCompra.getMovimientoalmacenList().remove(movimientoalmacen);
                idOrdenCompra = em.merge(idOrdenCompra);
            }
            List<Detallemovimiento> detallemovimientoList = movimientoalmacen.getDetallemovimientoList();
            for (Detallemovimiento detallemovimientoListDetallemovimiento : detallemovimientoList) {
                detallemovimientoListDetallemovimiento.setIdMovimiento(null);
                detallemovimientoListDetallemovimiento = em.merge(detallemovimientoListDetallemovimiento);
            }
            em.remove(movimientoalmacen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimientoalmacen> findMovimientoalmacenEntities() {
        return findMovimientoalmacenEntities(true, -1, -1);
    }

    public List<Movimientoalmacen> findMovimientoalmacenEntities(int maxResults, int firstResult) {
        return findMovimientoalmacenEntities(false, maxResults, firstResult);
    }

    private List<Movimientoalmacen> findMovimientoalmacenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimientoalmacen.class));
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

    public Movimientoalmacen findMovimientoalmacen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimientoalmacen.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoalmacenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimientoalmacen> rt = cq.from(Movimientoalmacen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
