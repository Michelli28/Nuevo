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
import model.controllers.exceptions.PreexistingEntityException;
import model.entities.Estadopago;
import model.entities.Pagos;
import model.entities.Pedido;

/**
 *
 * @author CHELLI BONITA
 */
public class PagosJpaController implements Serializable {

    public PagosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagos pagos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopago idEstadoPago = pagos.getIdEstadoPago();
            if (idEstadoPago != null) {
                idEstadoPago = em.getReference(idEstadoPago.getClass(), idEstadoPago.getIdEstadoPago());
                pagos.setIdEstadoPago(idEstadoPago);
            }
            Pedido idPedido = pagos.getIdPedido();
            if (idPedido != null) {
                idPedido = em.getReference(idPedido.getClass(), idPedido.getIdPedido());
                pagos.setIdPedido(idPedido);
            }
            em.persist(pagos);
            if (idEstadoPago != null) {
                idEstadoPago.getPagosList().add(pagos);
                idEstadoPago = em.merge(idEstadoPago);
            }
            if (idPedido != null) {
                idPedido.getPagosList().add(pagos);
                idPedido = em.merge(idPedido);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPagos(pagos.getNumeroOperacion()) != null) {
                throw new PreexistingEntityException("Pagos " + pagos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagos pagos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagos persistentPagos = em.find(Pagos.class, pagos.getNumeroOperacion());
            Estadopago idEstadoPagoOld = persistentPagos.getIdEstadoPago();
            Estadopago idEstadoPagoNew = pagos.getIdEstadoPago();
            Pedido idPedidoOld = persistentPagos.getIdPedido();
            Pedido idPedidoNew = pagos.getIdPedido();
            if (idEstadoPagoNew != null) {
                idEstadoPagoNew = em.getReference(idEstadoPagoNew.getClass(), idEstadoPagoNew.getIdEstadoPago());
                pagos.setIdEstadoPago(idEstadoPagoNew);
            }
            if (idPedidoNew != null) {
                idPedidoNew = em.getReference(idPedidoNew.getClass(), idPedidoNew.getIdPedido());
                pagos.setIdPedido(idPedidoNew);
            }
            pagos = em.merge(pagos);
            if (idEstadoPagoOld != null && !idEstadoPagoOld.equals(idEstadoPagoNew)) {
                idEstadoPagoOld.getPagosList().remove(pagos);
                idEstadoPagoOld = em.merge(idEstadoPagoOld);
            }
            if (idEstadoPagoNew != null && !idEstadoPagoNew.equals(idEstadoPagoOld)) {
                idEstadoPagoNew.getPagosList().add(pagos);
                idEstadoPagoNew = em.merge(idEstadoPagoNew);
            }
            if (idPedidoOld != null && !idPedidoOld.equals(idPedidoNew)) {
                idPedidoOld.getPagosList().remove(pagos);
                idPedidoOld = em.merge(idPedidoOld);
            }
            if (idPedidoNew != null && !idPedidoNew.equals(idPedidoOld)) {
                idPedidoNew.getPagosList().add(pagos);
                idPedidoNew = em.merge(idPedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pagos.getNumeroOperacion();
                if (findPagos(id) == null) {
                    throw new NonexistentEntityException("The pagos with id " + id + " no longer exists.");
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
            Pagos pagos;
            try {
                pagos = em.getReference(Pagos.class, id);
                pagos.getNumeroOperacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagos with id " + id + " no longer exists.", enfe);
            }
            Estadopago idEstadoPago = pagos.getIdEstadoPago();
            if (idEstadoPago != null) {
                idEstadoPago.getPagosList().remove(pagos);
                idEstadoPago = em.merge(idEstadoPago);
            }
            Pedido idPedido = pagos.getIdPedido();
            if (idPedido != null) {
                idPedido.getPagosList().remove(pagos);
                idPedido = em.merge(idPedido);
            }
            em.remove(pagos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagos> findPagosEntities() {
        return findPagosEntities(true, -1, -1);
    }

    public List<Pagos> findPagosEntities(int maxResults, int firstResult) {
        return findPagosEntities(false, maxResults, firstResult);
    }

    private List<Pagos> findPagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagos.class));
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

    public Pagos findPagos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagos> rt = cq.from(Pagos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
