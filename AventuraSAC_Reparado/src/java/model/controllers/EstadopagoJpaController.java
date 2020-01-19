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
import model.entities.Pagos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Estadopago;

/**
 *
 * @author CHELLI BONITA
 */
public class EstadopagoJpaController implements Serializable {

    public EstadopagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadopago estadopago) {
        if (estadopago.getPagosList() == null) {
            estadopago.setPagosList(new ArrayList<Pagos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pagos> attachedPagosList = new ArrayList<Pagos>();
            for (Pagos pagosListPagosToAttach : estadopago.getPagosList()) {
                pagosListPagosToAttach = em.getReference(pagosListPagosToAttach.getClass(), pagosListPagosToAttach.getNumeroOperacion());
                attachedPagosList.add(pagosListPagosToAttach);
            }
            estadopago.setPagosList(attachedPagosList);
            em.persist(estadopago);
            for (Pagos pagosListPagos : estadopago.getPagosList()) {
                Estadopago oldIdEstadoPagoOfPagosListPagos = pagosListPagos.getIdEstadoPago();
                pagosListPagos.setIdEstadoPago(estadopago);
                pagosListPagos = em.merge(pagosListPagos);
                if (oldIdEstadoPagoOfPagosListPagos != null) {
                    oldIdEstadoPagoOfPagosListPagos.getPagosList().remove(pagosListPagos);
                    oldIdEstadoPagoOfPagosListPagos = em.merge(oldIdEstadoPagoOfPagosListPagos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadopago estadopago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopago persistentEstadopago = em.find(Estadopago.class, estadopago.getIdEstadoPago());
            List<Pagos> pagosListOld = persistentEstadopago.getPagosList();
            List<Pagos> pagosListNew = estadopago.getPagosList();
            List<Pagos> attachedPagosListNew = new ArrayList<Pagos>();
            for (Pagos pagosListNewPagosToAttach : pagosListNew) {
                pagosListNewPagosToAttach = em.getReference(pagosListNewPagosToAttach.getClass(), pagosListNewPagosToAttach.getNumeroOperacion());
                attachedPagosListNew.add(pagosListNewPagosToAttach);
            }
            pagosListNew = attachedPagosListNew;
            estadopago.setPagosList(pagosListNew);
            estadopago = em.merge(estadopago);
            for (Pagos pagosListOldPagos : pagosListOld) {
                if (!pagosListNew.contains(pagosListOldPagos)) {
                    pagosListOldPagos.setIdEstadoPago(null);
                    pagosListOldPagos = em.merge(pagosListOldPagos);
                }
            }
            for (Pagos pagosListNewPagos : pagosListNew) {
                if (!pagosListOld.contains(pagosListNewPagos)) {
                    Estadopago oldIdEstadoPagoOfPagosListNewPagos = pagosListNewPagos.getIdEstadoPago();
                    pagosListNewPagos.setIdEstadoPago(estadopago);
                    pagosListNewPagos = em.merge(pagosListNewPagos);
                    if (oldIdEstadoPagoOfPagosListNewPagos != null && !oldIdEstadoPagoOfPagosListNewPagos.equals(estadopago)) {
                        oldIdEstadoPagoOfPagosListNewPagos.getPagosList().remove(pagosListNewPagos);
                        oldIdEstadoPagoOfPagosListNewPagos = em.merge(oldIdEstadoPagoOfPagosListNewPagos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadopago.getIdEstadoPago();
                if (findEstadopago(id) == null) {
                    throw new NonexistentEntityException("The estadopago with id " + id + " no longer exists.");
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
            Estadopago estadopago;
            try {
                estadopago = em.getReference(Estadopago.class, id);
                estadopago.getIdEstadoPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadopago with id " + id + " no longer exists.", enfe);
            }
            List<Pagos> pagosList = estadopago.getPagosList();
            for (Pagos pagosListPagos : pagosList) {
                pagosListPagos.setIdEstadoPago(null);
                pagosListPagos = em.merge(pagosListPagos);
            }
            em.remove(estadopago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadopago> findEstadopagoEntities() {
        return findEstadopagoEntities(true, -1, -1);
    }

    public List<Estadopago> findEstadopagoEntities(int maxResults, int firstResult) {
        return findEstadopagoEntities(false, maxResults, firstResult);
    }

    private List<Estadopago> findEstadopagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadopago.class));
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

    public Estadopago findEstadopago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadopago.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadopagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadopago> rt = cq.from(Estadopago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
