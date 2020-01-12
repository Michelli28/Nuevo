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
import model.entities.Distrito;
import model.entities.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Cliente;
import model.entities.Fichatecnica;

/**
 *
 * @author CHELLI BONITA
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getPedidoList() == null) {
            cliente.setPedidoList(new ArrayList<Pedido>());
        }
        if (cliente.getFichatecnicaList() == null) {
            cliente.setFichatecnicaList(new ArrayList<Fichatecnica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distrito idDistrito = cliente.getIdDistrito();
            if (idDistrito != null) {
                idDistrito = em.getReference(idDistrito.getClass(), idDistrito.getIdDistrito());
                cliente.setIdDistrito(idDistrito);
            }
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : cliente.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdPedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            cliente.setPedidoList(attachedPedidoList);
            List<Fichatecnica> attachedFichatecnicaList = new ArrayList<Fichatecnica>();
            for (Fichatecnica fichatecnicaListFichatecnicaToAttach : cliente.getFichatecnicaList()) {
                fichatecnicaListFichatecnicaToAttach = em.getReference(fichatecnicaListFichatecnicaToAttach.getClass(), fichatecnicaListFichatecnicaToAttach.getIdFicha());
                attachedFichatecnicaList.add(fichatecnicaListFichatecnicaToAttach);
            }
            cliente.setFichatecnicaList(attachedFichatecnicaList);
            em.persist(cliente);
            if (idDistrito != null) {
                idDistrito.getClienteList().add(cliente);
                idDistrito = em.merge(idDistrito);
            }
            for (Pedido pedidoListPedido : cliente.getPedidoList()) {
                Cliente oldIdClienteOfPedidoListPedido = pedidoListPedido.getIdCliente();
                pedidoListPedido.setIdCliente(cliente);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldIdClienteOfPedidoListPedido != null) {
                    oldIdClienteOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldIdClienteOfPedidoListPedido = em.merge(oldIdClienteOfPedidoListPedido);
                }
            }
            for (Fichatecnica fichatecnicaListFichatecnica : cliente.getFichatecnicaList()) {
                Cliente oldIdClienteOfFichatecnicaListFichatecnica = fichatecnicaListFichatecnica.getIdCliente();
                fichatecnicaListFichatecnica.setIdCliente(cliente);
                fichatecnicaListFichatecnica = em.merge(fichatecnicaListFichatecnica);
                if (oldIdClienteOfFichatecnicaListFichatecnica != null) {
                    oldIdClienteOfFichatecnicaListFichatecnica.getFichatecnicaList().remove(fichatecnicaListFichatecnica);
                    oldIdClienteOfFichatecnicaListFichatecnica = em.merge(oldIdClienteOfFichatecnicaListFichatecnica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Distrito idDistritoOld = persistentCliente.getIdDistrito();
            Distrito idDistritoNew = cliente.getIdDistrito();
            List<Pedido> pedidoListOld = persistentCliente.getPedidoList();
            List<Pedido> pedidoListNew = cliente.getPedidoList();
            List<Fichatecnica> fichatecnicaListOld = persistentCliente.getFichatecnicaList();
            List<Fichatecnica> fichatecnicaListNew = cliente.getFichatecnicaList();
            if (idDistritoNew != null) {
                idDistritoNew = em.getReference(idDistritoNew.getClass(), idDistritoNew.getIdDistrito());
                cliente.setIdDistrito(idDistritoNew);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            if(pedidoListNew != null){
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdPedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }}
            pedidoListNew = attachedPedidoListNew;
            cliente.setPedidoList(pedidoListNew);
            List<Fichatecnica> attachedFichatecnicaListNew = new ArrayList<Fichatecnica>();
            if(fichatecnicaListNew != null){
            for (Fichatecnica fichatecnicaListNewFichatecnicaToAttach : fichatecnicaListNew) {
                fichatecnicaListNewFichatecnicaToAttach = em.getReference(fichatecnicaListNewFichatecnicaToAttach.getClass(), fichatecnicaListNewFichatecnicaToAttach.getIdFicha());
                attachedFichatecnicaListNew.add(fichatecnicaListNewFichatecnicaToAttach);
            }}
            fichatecnicaListNew = attachedFichatecnicaListNew;
            cliente.setFichatecnicaList(fichatecnicaListNew);
            cliente = em.merge(cliente);
            if (idDistritoOld != null && !idDistritoOld.equals(idDistritoNew)) {
                idDistritoOld.getClienteList().remove(cliente);
                idDistritoOld = em.merge(idDistritoOld);
            }
            if (idDistritoNew != null && !idDistritoNew.equals(idDistritoOld)) {
                idDistritoNew.getClienteList().add(cliente);
                idDistritoNew = em.merge(idDistritoNew);
            }
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setIdCliente(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Cliente oldIdClienteOfPedidoListNewPedido = pedidoListNewPedido.getIdCliente();
                    pedidoListNewPedido.setIdCliente(cliente);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldIdClienteOfPedidoListNewPedido != null && !oldIdClienteOfPedidoListNewPedido.equals(cliente)) {
                        oldIdClienteOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldIdClienteOfPedidoListNewPedido = em.merge(oldIdClienteOfPedidoListNewPedido);
                    }
                }
            }
            for (Fichatecnica fichatecnicaListOldFichatecnica : fichatecnicaListOld) {
                if (!fichatecnicaListNew.contains(fichatecnicaListOldFichatecnica)) {
                    fichatecnicaListOldFichatecnica.setIdCliente(null);
                    fichatecnicaListOldFichatecnica = em.merge(fichatecnicaListOldFichatecnica);
                }
            }
            for (Fichatecnica fichatecnicaListNewFichatecnica : fichatecnicaListNew) {
                if (!fichatecnicaListOld.contains(fichatecnicaListNewFichatecnica)) {
                    Cliente oldIdClienteOfFichatecnicaListNewFichatecnica = fichatecnicaListNewFichatecnica.getIdCliente();
                    fichatecnicaListNewFichatecnica.setIdCliente(cliente);
                    fichatecnicaListNewFichatecnica = em.merge(fichatecnicaListNewFichatecnica);
                    if (oldIdClienteOfFichatecnicaListNewFichatecnica != null && !oldIdClienteOfFichatecnicaListNewFichatecnica.equals(cliente)) {
                        oldIdClienteOfFichatecnicaListNewFichatecnica.getFichatecnicaList().remove(fichatecnicaListNewFichatecnica);
                        oldIdClienteOfFichatecnicaListNewFichatecnica = em.merge(oldIdClienteOfFichatecnicaListNewFichatecnica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Distrito idDistrito = cliente.getIdDistrito();
            if (idDistrito != null) {
                idDistrito.getClienteList().remove(cliente);
                idDistrito = em.merge(idDistrito);
            }
            List<Pedido> pedidoList = cliente.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setIdCliente(null);
                pedidoListPedido = em.merge(pedidoListPedido);
            }
            List<Fichatecnica> fichatecnicaList = cliente.getFichatecnicaList();
            for (Fichatecnica fichatecnicaListFichatecnica : fichatecnicaList) {
                fichatecnicaListFichatecnica.setIdCliente(null);
                fichatecnicaListFichatecnica = em.merge(fichatecnicaListFichatecnica);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
