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
import model.entities.Cargo;
import model.entities.Areas;
import model.entities.Ordencompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Empleado;

/**
 *
 * @author CHELLI BONITA
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getOrdencompraList() == null) {
            empleado.setOrdencompraList(new ArrayList<Ordencompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo idCargo = empleado.getIdCargo();
            if (idCargo != null) {
                idCargo = em.getReference(idCargo.getClass(), idCargo.getIdCargo());
                empleado.setIdCargo(idCargo);
            }
            Areas idArea = empleado.getIdArea();
            if (idArea != null) {
                idArea = em.getReference(idArea.getClass(), idArea.getIdArea());
                empleado.setIdArea(idArea);
            }
            List<Ordencompra> attachedOrdencompraList = new ArrayList<Ordencompra>();
            for (Ordencompra ordencompraListOrdencompraToAttach : empleado.getOrdencompraList()) {
                ordencompraListOrdencompraToAttach = em.getReference(ordencompraListOrdencompraToAttach.getClass(), ordencompraListOrdencompraToAttach.getIdOrdenCompra());
                attachedOrdencompraList.add(ordencompraListOrdencompraToAttach);
            }
            empleado.setOrdencompraList(attachedOrdencompraList);
            em.persist(empleado);
            if (idCargo != null) {
                idCargo.getEmpleadoList().add(empleado);
                idCargo = em.merge(idCargo);
            }
            if (idArea != null) {
                idArea.getEmpleadoList().add(empleado);
                idArea = em.merge(idArea);
            }
            for (Ordencompra ordencompraListOrdencompra : empleado.getOrdencompraList()) {
                Empleado oldIdEmpleadoOfOrdencompraListOrdencompra = ordencompraListOrdencompra.getIdEmpleado();
                ordencompraListOrdencompra.setIdEmpleado(empleado);
                ordencompraListOrdencompra = em.merge(ordencompraListOrdencompra);
                if (oldIdEmpleadoOfOrdencompraListOrdencompra != null) {
                    oldIdEmpleadoOfOrdencompraListOrdencompra.getOrdencompraList().remove(ordencompraListOrdencompra);
                    oldIdEmpleadoOfOrdencompraListOrdencompra = em.merge(oldIdEmpleadoOfOrdencompraListOrdencompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdEmpleado());
            Cargo idCargoOld = persistentEmpleado.getIdCargo();
            Cargo idCargoNew = empleado.getIdCargo();
            Areas idAreaOld = persistentEmpleado.getIdArea();
            Areas idAreaNew = empleado.getIdArea();
            List<Ordencompra> ordencompraListOld = persistentEmpleado.getOrdencompraList();
            List<Ordencompra> ordencompraListNew = empleado.getOrdencompraList();
            if (idCargoNew != null) {
                idCargoNew = em.getReference(idCargoNew.getClass(), idCargoNew.getIdCargo());
                empleado.setIdCargo(idCargoNew);
            }
            if (idAreaNew != null) {
                idAreaNew = em.getReference(idAreaNew.getClass(), idAreaNew.getIdArea());
                empleado.setIdArea(idAreaNew);
            }
            List<Ordencompra> attachedOrdencompraListNew = new ArrayList<Ordencompra>();
            if(ordencompraListNew != null){
            for (Ordencompra ordencompraListNewOrdencompraToAttach : ordencompraListNew) {
                ordencompraListNewOrdencompraToAttach = em.getReference(ordencompraListNewOrdencompraToAttach.getClass(), ordencompraListNewOrdencompraToAttach.getIdOrdenCompra());
                attachedOrdencompraListNew.add(ordencompraListNewOrdencompraToAttach);
            }}
            ordencompraListNew = attachedOrdencompraListNew;
            empleado.setOrdencompraList(ordencompraListNew);
            empleado = em.merge(empleado);
            if (idCargoOld != null && !idCargoOld.equals(idCargoNew)) {
                idCargoOld.getEmpleadoList().remove(empleado);
                idCargoOld = em.merge(idCargoOld);
            }
            if (idCargoNew != null && !idCargoNew.equals(idCargoOld)) {
                idCargoNew.getEmpleadoList().add(empleado);
                idCargoNew = em.merge(idCargoNew);
            }
            if (idAreaOld != null && !idAreaOld.equals(idAreaNew)) {
                idAreaOld.getEmpleadoList().remove(empleado);
                idAreaOld = em.merge(idAreaOld);
            }
            if (idAreaNew != null && !idAreaNew.equals(idAreaOld)) {
                idAreaNew.getEmpleadoList().add(empleado);
                idAreaNew = em.merge(idAreaNew);
            }
            for (Ordencompra ordencompraListOldOrdencompra : ordencompraListOld) {
                if (!ordencompraListNew.contains(ordencompraListOldOrdencompra)) {
                    ordencompraListOldOrdencompra.setIdEmpleado(null);
                    ordencompraListOldOrdencompra = em.merge(ordencompraListOldOrdencompra);
                }
            }
            for (Ordencompra ordencompraListNewOrdencompra : ordencompraListNew) {
                if (!ordencompraListOld.contains(ordencompraListNewOrdencompra)) {
                    Empleado oldIdEmpleadoOfOrdencompraListNewOrdencompra = ordencompraListNewOrdencompra.getIdEmpleado();
                    ordencompraListNewOrdencompra.setIdEmpleado(empleado);
                    ordencompraListNewOrdencompra = em.merge(ordencompraListNewOrdencompra);
                    if (oldIdEmpleadoOfOrdencompraListNewOrdencompra != null && !oldIdEmpleadoOfOrdencompraListNewOrdencompra.equals(empleado)) {
                        oldIdEmpleadoOfOrdencompraListNewOrdencompra.getOrdencompraList().remove(ordencompraListNewOrdencompra);
                        oldIdEmpleadoOfOrdencompraListNewOrdencompra = em.merge(oldIdEmpleadoOfOrdencompraListNewOrdencompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getIdEmpleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            Cargo idCargo = empleado.getIdCargo();
            if (idCargo != null) {
                idCargo.getEmpleadoList().remove(empleado);
                idCargo = em.merge(idCargo);
            }
            Areas idArea = empleado.getIdArea();
            if (idArea != null) {
                idArea.getEmpleadoList().remove(empleado);
                idArea = em.merge(idArea);
            }
            List<Ordencompra> ordencompraList = empleado.getOrdencompraList();
            for (Ordencompra ordencompraListOrdencompra : ordencompraList) {
                ordencompraListOrdencompra.setIdEmpleado(null);
                ordencompraListOrdencompra = em.merge(ordencompraListOrdencompra);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
