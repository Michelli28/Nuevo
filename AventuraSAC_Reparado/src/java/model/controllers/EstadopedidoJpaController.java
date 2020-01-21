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
import model.entities.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.IllegalOrphanException;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Estadopedido;

/**
 *
 * @author CHELLI BONITA
 */
public class EstadopedidoJpaController implements Serializable {

    public EstadopedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadopedido estadopedido) {
        if (estadopedido.getPedidoList() == null) {
            estadopedido.setPedidoList(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : estadopedido.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdPedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            estadopedido.setPedidoList(attachedPedidoList);
            em.persist(estadopedido);
            for (Pedido pedidoListPedido : estadopedido.getPedidoList()) {
                Estadopedido oldIdEstadoOfPedidoListPedido = pedidoListPedido.getIdEstado();
                pedidoListPedido.setIdEstado(estadopedido);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldIdEstadoOfPedidoListPedido != null) {
                    oldIdEstadoOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldIdEstadoOfPedidoListPedido = em.merge(oldIdEstadoOfPedidoListPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadopedido estadopedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopedido persistentEstadopedido = em.find(Estadopedido.class, estadopedido.getIdEstado());
            List<Pedido> pedidoListOld = persistentEstadopedido.getPedidoList();
            List<Pedido> pedidoListNew = estadopedido.getPedidoList();
            List<String> illegalOrphanMessages = null;
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoListOldPedido + " since its idEstado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdPedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            estadopedido.setPedidoList(pedidoListNew);
            estadopedido = em.merge(estadopedido);
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Estadopedido oldIdEstadoOfPedidoListNewPedido = pedidoListNewPedido.getIdEstado();
                    pedidoListNewPedido.setIdEstado(estadopedido);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldIdEstadoOfPedidoListNewPedido != null && !oldIdEstadoOfPedidoListNewPedido.equals(estadopedido)) {
                        oldIdEstadoOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldIdEstadoOfPedidoListNewPedido = em.merge(oldIdEstadoOfPedidoListNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadopedido.getIdEstado();
                if (findEstadopedido(id) == null) {
                    throw new NonexistentEntityException("The estadopedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopedido estadopedido;
            try {
                estadopedido = em.getReference(Estadopedido.class, id);
                estadopedido.getIdEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadopedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pedido> pedidoListOrphanCheck = estadopedido.getPedidoList();
            for (Pedido pedidoListOrphanCheckPedido : pedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estadopedido (" + estadopedido + ") cannot be destroyed since the Pedido " + pedidoListOrphanCheckPedido + " in its pedidoList field has a non-nullable idEstado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadopedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadopedido> findEstadopedidoEntities() {
        return findEstadopedidoEntities(true, -1, -1);
    }

    public List<Estadopedido> findEstadopedidoEntities(int maxResults, int firstResult) {
        return findEstadopedidoEntities(false, maxResults, firstResult);
    }

    private List<Estadopedido> findEstadopedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadopedido.class));
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

    public Estadopedido findEstadopedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadopedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadopedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadopedido> rt = cq.from(Estadopedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
