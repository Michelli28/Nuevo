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
import model.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Distrito;
import model.entities.Proveedor;

/**
 *
 * @author CHELLI BONITA
 */
public class DistritoJpaController implements Serializable {

    public DistritoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Distrito distrito) {
        if (distrito.getClienteList() == null) {
            distrito.setClienteList(new ArrayList<Cliente>());
        }
        if (distrito.getProveedorList() == null) {
            distrito.setProveedorList(new ArrayList<Proveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : distrito.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdCliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            distrito.setClienteList(attachedClienteList);
            List<Proveedor> attachedProveedorList = new ArrayList<Proveedor>();
            for (Proveedor proveedorListProveedorToAttach : distrito.getProveedorList()) {
                proveedorListProveedorToAttach = em.getReference(proveedorListProveedorToAttach.getClass(), proveedorListProveedorToAttach.getIdProveedor());
                attachedProveedorList.add(proveedorListProveedorToAttach);
            }
            distrito.setProveedorList(attachedProveedorList);
            em.persist(distrito);
            for (Cliente clienteListCliente : distrito.getClienteList()) {
                Distrito oldIdDistritoOfClienteListCliente = clienteListCliente.getIdDistrito();
                clienteListCliente.setIdDistrito(distrito);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldIdDistritoOfClienteListCliente != null) {
                    oldIdDistritoOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldIdDistritoOfClienteListCliente = em.merge(oldIdDistritoOfClienteListCliente);
                }
            }
            for (Proveedor proveedorListProveedor : distrito.getProveedorList()) {
                Distrito oldIdDistritoOfProveedorListProveedor = proveedorListProveedor.getIdDistrito();
                proveedorListProveedor.setIdDistrito(distrito);
                proveedorListProveedor = em.merge(proveedorListProveedor);
                if (oldIdDistritoOfProveedorListProveedor != null) {
                    oldIdDistritoOfProveedorListProveedor.getProveedorList().remove(proveedorListProveedor);
                    oldIdDistritoOfProveedorListProveedor = em.merge(oldIdDistritoOfProveedorListProveedor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Distrito distrito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distrito persistentDistrito = em.find(Distrito.class, distrito.getIdDistrito());
            List<Cliente> clienteListOld = persistentDistrito.getClienteList();
            List<Cliente> clienteListNew = distrito.getClienteList();
            List<Proveedor> proveedorListOld = persistentDistrito.getProveedorList();
            List<Proveedor> proveedorListNew = distrito.getProveedorList();
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdCliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            distrito.setClienteList(clienteListNew);
            List<Proveedor> attachedProveedorListNew = new ArrayList<Proveedor>();
            for (Proveedor proveedorListNewProveedorToAttach : proveedorListNew) {
                proveedorListNewProveedorToAttach = em.getReference(proveedorListNewProveedorToAttach.getClass(), proveedorListNewProveedorToAttach.getIdProveedor());
                attachedProveedorListNew.add(proveedorListNewProveedorToAttach);
            }
            proveedorListNew = attachedProveedorListNew;
            distrito.setProveedorList(proveedorListNew);
            distrito = em.merge(distrito);
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setIdDistrito(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Distrito oldIdDistritoOfClienteListNewCliente = clienteListNewCliente.getIdDistrito();
                    clienteListNewCliente.setIdDistrito(distrito);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldIdDistritoOfClienteListNewCliente != null && !oldIdDistritoOfClienteListNewCliente.equals(distrito)) {
                        oldIdDistritoOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldIdDistritoOfClienteListNewCliente = em.merge(oldIdDistritoOfClienteListNewCliente);
                    }
                }
            }
            for (Proveedor proveedorListOldProveedor : proveedorListOld) {
                if (!proveedorListNew.contains(proveedorListOldProveedor)) {
                    proveedorListOldProveedor.setIdDistrito(null);
                    proveedorListOldProveedor = em.merge(proveedorListOldProveedor);
                }
            }
            for (Proveedor proveedorListNewProveedor : proveedorListNew) {
                if (!proveedorListOld.contains(proveedorListNewProveedor)) {
                    Distrito oldIdDistritoOfProveedorListNewProveedor = proveedorListNewProveedor.getIdDistrito();
                    proveedorListNewProveedor.setIdDistrito(distrito);
                    proveedorListNewProveedor = em.merge(proveedorListNewProveedor);
                    if (oldIdDistritoOfProveedorListNewProveedor != null && !oldIdDistritoOfProveedorListNewProveedor.equals(distrito)) {
                        oldIdDistritoOfProveedorListNewProveedor.getProveedorList().remove(proveedorListNewProveedor);
                        oldIdDistritoOfProveedorListNewProveedor = em.merge(oldIdDistritoOfProveedorListNewProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = distrito.getIdDistrito();
                if (findDistrito(id) == null) {
                    throw new NonexistentEntityException("The distrito with id " + id + " no longer exists.");
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
            Distrito distrito;
            try {
                distrito = em.getReference(Distrito.class, id);
                distrito.getIdDistrito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The distrito with id " + id + " no longer exists.", enfe);
            }
            List<Cliente> clienteList = distrito.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setIdDistrito(null);
                clienteListCliente = em.merge(clienteListCliente);
            }
            List<Proveedor> proveedorList = distrito.getProveedorList();
            for (Proveedor proveedorListProveedor : proveedorList) {
                proveedorListProveedor.setIdDistrito(null);
                proveedorListProveedor = em.merge(proveedorListProveedor);
            }
            em.remove(distrito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Distrito> findDistritoEntities() {
        return findDistritoEntities(true, -1, -1);
    }

    public List<Distrito> findDistritoEntities(int maxResults, int firstResult) {
        return findDistritoEntities(false, maxResults, firstResult);
    }

    private List<Distrito> findDistritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Distrito.class));
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

    public Distrito findDistrito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Distrito.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Distrito> rt = cq.from(Distrito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
