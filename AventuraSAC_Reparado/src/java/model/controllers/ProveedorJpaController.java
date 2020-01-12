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
import model.entities.Ordencompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Proveedor;

/**
 *
 * @author CHELLI BONITA
 */
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) {
        if (proveedor.getOrdencompraList() == null) {
            proveedor.setOrdencompraList(new ArrayList<Ordencompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distrito idDistrito = proveedor.getIdDistrito();
            if (idDistrito != null) {
                idDistrito = em.getReference(idDistrito.getClass(), idDistrito.getIdDistrito());
                proveedor.setIdDistrito(idDistrito);
            }
            List<Ordencompra> attachedOrdencompraList = new ArrayList<Ordencompra>();
            for (Ordencompra ordencompraListOrdencompraToAttach : proveedor.getOrdencompraList()) {
                ordencompraListOrdencompraToAttach = em.getReference(ordencompraListOrdencompraToAttach.getClass(), ordencompraListOrdencompraToAttach.getIdOrdenCompra());
                attachedOrdencompraList.add(ordencompraListOrdencompraToAttach);
            }
            proveedor.setOrdencompraList(attachedOrdencompraList);
            em.persist(proveedor);
            if (idDistrito != null) {
                idDistrito.getProveedorList().add(proveedor);
                idDistrito = em.merge(idDistrito);
            }
            for (Ordencompra ordencompraListOrdencompra : proveedor.getOrdencompraList()) {
                Proveedor oldIdProveedorOfOrdencompraListOrdencompra = ordencompraListOrdencompra.getIdProveedor();
                ordencompraListOrdencompra.setIdProveedor(proveedor);
                ordencompraListOrdencompra = em.merge(ordencompraListOrdencompra);
                if (oldIdProveedorOfOrdencompraListOrdencompra != null) {
                    oldIdProveedorOfOrdencompraListOrdencompra.getOrdencompraList().remove(ordencompraListOrdencompra);
                    oldIdProveedorOfOrdencompraListOrdencompra = em.merge(oldIdProveedorOfOrdencompraListOrdencompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getIdProveedor());
            Distrito idDistritoOld = persistentProveedor.getIdDistrito();
            Distrito idDistritoNew = proveedor.getIdDistrito();
            List<Ordencompra> ordencompraListOld = persistentProveedor.getOrdencompraList();
            List<Ordencompra> ordencompraListNew = proveedor.getOrdencompraList();
            if (idDistritoNew != null) {
                idDistritoNew = em.getReference(idDistritoNew.getClass(), idDistritoNew.getIdDistrito());
                proveedor.setIdDistrito(idDistritoNew);
            }
            List<Ordencompra> attachedOrdencompraListNew = new ArrayList<Ordencompra>();
            if(ordencompraListNew != null){
            for (Ordencompra ordencompraListNewOrdencompraToAttach : ordencompraListNew) {
                ordencompraListNewOrdencompraToAttach = em.getReference(ordencompraListNewOrdencompraToAttach.getClass(), ordencompraListNewOrdencompraToAttach.getIdOrdenCompra());
                attachedOrdencompraListNew.add(ordencompraListNewOrdencompraToAttach);
            }}
            ordencompraListNew = attachedOrdencompraListNew;
            proveedor.setOrdencompraList(ordencompraListNew);
            proveedor = em.merge(proveedor);
            if (idDistritoOld != null && !idDistritoOld.equals(idDistritoNew)) {
                idDistritoOld.getProveedorList().remove(proveedor);
                idDistritoOld = em.merge(idDistritoOld);
            }
            if (idDistritoNew != null && !idDistritoNew.equals(idDistritoOld)) {
                idDistritoNew.getProveedorList().add(proveedor);
                idDistritoNew = em.merge(idDistritoNew);
            }
            for (Ordencompra ordencompraListOldOrdencompra : ordencompraListOld) {
                if (!ordencompraListNew.contains(ordencompraListOldOrdencompra)) {
                    ordencompraListOldOrdencompra.setIdProveedor(null);
                    ordencompraListOldOrdencompra = em.merge(ordencompraListOldOrdencompra);
                }
            }
            for (Ordencompra ordencompraListNewOrdencompra : ordencompraListNew) {
                if (!ordencompraListOld.contains(ordencompraListNewOrdencompra)) {
                    Proveedor oldIdProveedorOfOrdencompraListNewOrdencompra = ordencompraListNewOrdencompra.getIdProveedor();
                    ordencompraListNewOrdencompra.setIdProveedor(proveedor);
                    ordencompraListNewOrdencompra = em.merge(ordencompraListNewOrdencompra);
                    if (oldIdProveedorOfOrdencompraListNewOrdencompra != null && !oldIdProveedorOfOrdencompraListNewOrdencompra.equals(proveedor)) {
                        oldIdProveedorOfOrdencompraListNewOrdencompra.getOrdencompraList().remove(ordencompraListNewOrdencompra);
                        oldIdProveedorOfOrdencompraListNewOrdencompra = em.merge(oldIdProveedorOfOrdencompraListNewOrdencompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getIdProveedor();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            Distrito idDistrito = proveedor.getIdDistrito();
            if (idDistrito != null) {
                idDistrito.getProveedorList().remove(proveedor);
                idDistrito = em.merge(idDistrito);
            }
            List<Ordencompra> ordencompraList = proveedor.getOrdencompraList();
            for (Ordencompra ordencompraListOrdencompra : ordencompraList) {
                ordencompraListOrdencompra.setIdProveedor(null);
                ordencompraListOrdencompra = em.merge(ordencompraListOrdencompra);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
