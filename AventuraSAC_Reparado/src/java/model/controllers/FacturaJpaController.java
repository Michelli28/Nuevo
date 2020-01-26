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
import model.entities.Cotizacion;
import model.entities.Detallefactura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Factura;

/**
 *
 * @author CHELLI BONITA
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getDetallefacturaList() == null) {
            factura.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotizacion idCotizacion = factura.getIdCotizacion();
            if (idCotizacion != null) {
                idCotizacion = em.getReference(idCotizacion.getClass(), idCotizacion.getIdCotizacion());
                factura.setIdCotizacion(idCotizacion);
            }
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : factura.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getIdDetalleFactura());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            factura.setDetallefacturaList(attachedDetallefacturaList);
            em.persist(factura);
            if (idCotizacion != null) {
                idCotizacion.getFacturaList().add(factura);
                idCotizacion = em.merge(idCotizacion);
            }
            for (Detallefactura detallefacturaListDetallefactura : factura.getDetallefacturaList()) {
                Factura oldIdFacturaOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getIdFactura();
                detallefacturaListDetallefactura.setIdFactura(factura);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldIdFacturaOfDetallefacturaListDetallefactura != null) {
                    oldIdFacturaOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldIdFacturaOfDetallefacturaListDetallefactura = em.merge(oldIdFacturaOfDetallefacturaListDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactura());
            Cotizacion idCotizacionOld = persistentFactura.getIdCotizacion();
            Cotizacion idCotizacionNew = factura.getIdCotizacion();
            List<Detallefactura> detallefacturaListOld = persistentFactura.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = factura.getDetallefacturaList();
            if (idCotizacionNew != null) {
                idCotizacionNew = em.getReference(idCotizacionNew.getClass(), idCotizacionNew.getIdCotizacion());
                factura.setIdCotizacion(idCotizacionNew);
            }
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getIdDetalleFactura());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            factura.setDetallefacturaList(detallefacturaListNew);
            factura = em.merge(factura);
            if (idCotizacionOld != null && !idCotizacionOld.equals(idCotizacionNew)) {
                idCotizacionOld.getFacturaList().remove(factura);
                idCotizacionOld = em.merge(idCotizacionOld);
            }
            if (idCotizacionNew != null && !idCotizacionNew.equals(idCotizacionOld)) {
                idCotizacionNew.getFacturaList().add(factura);
                idCotizacionNew = em.merge(idCotizacionNew);
            }
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    detallefacturaListOldDetallefactura.setIdFactura(null);
                    detallefacturaListOldDetallefactura = em.merge(detallefacturaListOldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Factura oldIdFacturaOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getIdFactura();
                    detallefacturaListNewDetallefactura.setIdFactura(factura);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldIdFacturaOfDetallefacturaListNewDetallefactura != null && !oldIdFacturaOfDetallefacturaListNewDetallefactura.equals(factura)) {
                        oldIdFacturaOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldIdFacturaOfDetallefacturaListNewDetallefactura = em.merge(oldIdFacturaOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            Cotizacion idCotizacion = factura.getIdCotizacion();
            if (idCotizacion != null) {
                idCotizacion.getFacturaList().remove(factura);
                idCotizacion = em.merge(idCotizacion);
            }
            List<Detallefactura> detallefacturaList = factura.getDetallefacturaList();
            for (Detallefactura detallefacturaListDetallefactura : detallefacturaList) {
                detallefacturaListDetallefactura.setIdFactura(null);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
