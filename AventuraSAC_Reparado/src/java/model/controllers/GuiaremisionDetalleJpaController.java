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
import model.entities.Pedido;
import model.entities.Guiaremision;
import model.entities.GuiaremisionDetalle;

/**
 *
 * @author CHELLI BONITA
 */
public class GuiaremisionDetalleJpaController implements Serializable {

    public GuiaremisionDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GuiaremisionDetalle guiaremisionDetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido idPedido = guiaremisionDetalle.getIdPedido();
            if (idPedido != null) {
                idPedido = em.getReference(idPedido.getClass(), idPedido.getIdPedido());
                guiaremisionDetalle.setIdPedido(idPedido);
            }
            Guiaremision idGuiaRemision = guiaremisionDetalle.getIdGuiaRemision();
            if (idGuiaRemision != null) {
                idGuiaRemision = em.getReference(idGuiaRemision.getClass(), idGuiaRemision.getIdGuiaRemision());
                guiaremisionDetalle.setIdGuiaRemision(idGuiaRemision);
            }
            em.persist(guiaremisionDetalle);
            if (idPedido != null) {
                idPedido.getGuiaremisionDetalleList().add(guiaremisionDetalle);
                idPedido = em.merge(idPedido);
            }
            if (idGuiaRemision != null) {
                idGuiaRemision.getGuiaremisionDetalleList().add(guiaremisionDetalle);
                idGuiaRemision = em.merge(idGuiaRemision);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GuiaremisionDetalle guiaremisionDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GuiaremisionDetalle persistentGuiaremisionDetalle = em.find(GuiaremisionDetalle.class, guiaremisionDetalle.getIdDetalleGuiaRemision());
            Pedido idPedidoOld = persistentGuiaremisionDetalle.getIdPedido();
            Pedido idPedidoNew = guiaremisionDetalle.getIdPedido();
            Guiaremision idGuiaRemisionOld = persistentGuiaremisionDetalle.getIdGuiaRemision();
            Guiaremision idGuiaRemisionNew = guiaremisionDetalle.getIdGuiaRemision();
            if (idPedidoNew != null) {
                idPedidoNew = em.getReference(idPedidoNew.getClass(), idPedidoNew.getIdPedido());
                guiaremisionDetalle.setIdPedido(idPedidoNew);
            }
            if (idGuiaRemisionNew != null) {
                idGuiaRemisionNew = em.getReference(idGuiaRemisionNew.getClass(), idGuiaRemisionNew.getIdGuiaRemision());
                guiaremisionDetalle.setIdGuiaRemision(idGuiaRemisionNew);
            }
            guiaremisionDetalle = em.merge(guiaremisionDetalle);
            if (idPedidoOld != null && !idPedidoOld.equals(idPedidoNew)) {
                idPedidoOld.getGuiaremisionDetalleList().remove(guiaremisionDetalle);
                idPedidoOld = em.merge(idPedidoOld);
            }
            if (idPedidoNew != null && !idPedidoNew.equals(idPedidoOld)) {
                idPedidoNew.getGuiaremisionDetalleList().add(guiaremisionDetalle);
                idPedidoNew = em.merge(idPedidoNew);
            }
            if (idGuiaRemisionOld != null && !idGuiaRemisionOld.equals(idGuiaRemisionNew)) {
                idGuiaRemisionOld.getGuiaremisionDetalleList().remove(guiaremisionDetalle);
                idGuiaRemisionOld = em.merge(idGuiaRemisionOld);
            }
            if (idGuiaRemisionNew != null && !idGuiaRemisionNew.equals(idGuiaRemisionOld)) {
                idGuiaRemisionNew.getGuiaremisionDetalleList().add(guiaremisionDetalle);
                idGuiaRemisionNew = em.merge(idGuiaRemisionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = guiaremisionDetalle.getIdDetalleGuiaRemision();
                if (findGuiaremisionDetalle(id) == null) {
                    throw new NonexistentEntityException("The guiaremisionDetalle with id " + id + " no longer exists.");
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
            GuiaremisionDetalle guiaremisionDetalle;
            try {
                guiaremisionDetalle = em.getReference(GuiaremisionDetalle.class, id);
                guiaremisionDetalle.getIdDetalleGuiaRemision();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The guiaremisionDetalle with id " + id + " no longer exists.", enfe);
            }
            Pedido idPedido = guiaremisionDetalle.getIdPedido();
            if (idPedido != null) {
                idPedido.getGuiaremisionDetalleList().remove(guiaremisionDetalle);
                idPedido = em.merge(idPedido);
            }
            Guiaremision idGuiaRemision = guiaremisionDetalle.getIdGuiaRemision();
            if (idGuiaRemision != null) {
                idGuiaRemision.getGuiaremisionDetalleList().remove(guiaremisionDetalle);
                idGuiaRemision = em.merge(idGuiaRemision);
            }
            em.remove(guiaremisionDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GuiaremisionDetalle> findGuiaremisionDetalleEntities() {
        return findGuiaremisionDetalleEntities(true, -1, -1);
    }

    public List<GuiaremisionDetalle> findGuiaremisionDetalleEntities(int maxResults, int firstResult) {
        return findGuiaremisionDetalleEntities(false, maxResults, firstResult);
    }

    private List<GuiaremisionDetalle> findGuiaremisionDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GuiaremisionDetalle.class));
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

    public GuiaremisionDetalle findGuiaremisionDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GuiaremisionDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getGuiaremisionDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GuiaremisionDetalle> rt = cq.from(GuiaremisionDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
