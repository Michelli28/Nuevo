/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.CotizacionDetalle;
import model.entities.Detallefactura;
import model.entities.Factura;

/**
 *
 * @author CHELLI BONITA
 */
public class DetallefacturaJpaController implements Serializable {

    public DetallefacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefactura detallefactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CotizacionDetalle idDetalleCotizacion = detallefactura.getIdDetalleCotizacion();
            if (idDetalleCotizacion != null) {
                idDetalleCotizacion = em.getReference(idDetalleCotizacion.getClass(), idDetalleCotizacion.getIdDetalleCotizacion());
                detallefactura.setIdDetalleCotizacion(idDetalleCotizacion);
            }
            Factura idFactura = detallefactura.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getIdFactura());
                detallefactura.setIdFactura(idFactura);
            }
            em.persist(detallefactura);
            if (idDetalleCotizacion != null) {
                idDetalleCotizacion.getDetallefacturaList().add(detallefactura);
                idDetalleCotizacion = em.merge(idDetalleCotizacion);
            }
            if (idFactura != null) {
                idFactura.getDetallefacturaList().add(detallefactura);
                idFactura = em.merge(idFactura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefactura detallefactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getIdDetalleFactura());
            CotizacionDetalle idDetalleCotizacionOld = persistentDetallefactura.getIdDetalleCotizacion();
            CotizacionDetalle idDetalleCotizacionNew = detallefactura.getIdDetalleCotizacion();
            Factura idFacturaOld = persistentDetallefactura.getIdFactura();
            Factura idFacturaNew = detallefactura.getIdFactura();
            if (idDetalleCotizacionNew != null) {
                idDetalleCotizacionNew = em.getReference(idDetalleCotizacionNew.getClass(), idDetalleCotizacionNew.getIdDetalleCotizacion());
                detallefactura.setIdDetalleCotizacion(idDetalleCotizacionNew);
            }
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getIdFactura());
                detallefactura.setIdFactura(idFacturaNew);
            }
            detallefactura = em.merge(detallefactura);
            if (idDetalleCotizacionOld != null && !idDetalleCotizacionOld.equals(idDetalleCotizacionNew)) {
                idDetalleCotizacionOld.getDetallefacturaList().remove(detallefactura);
                idDetalleCotizacionOld = em.merge(idDetalleCotizacionOld);
            }
            if (idDetalleCotizacionNew != null && !idDetalleCotizacionNew.equals(idDetalleCotizacionOld)) {
                idDetalleCotizacionNew.getDetallefacturaList().add(detallefactura);
                idDetalleCotizacionNew = em.merge(idDetalleCotizacionNew);
            }
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getDetallefacturaList().remove(detallefactura);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getDetallefacturaList().add(detallefactura);
                idFacturaNew = em.merge(idFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallefactura.getIdDetalleFactura();
                if (findDetallefactura(id) == null) {
                    throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.");
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
            Detallefactura detallefactura;
            try {
                detallefactura = em.getReference(Detallefactura.class, id);
                detallefactura.getIdDetalleFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.", enfe);
            }
            CotizacionDetalle idDetalleCotizacion = detallefactura.getIdDetalleCotizacion();
            if (idDetalleCotizacion != null) {
                idDetalleCotizacion.getDetallefacturaList().remove(detallefactura);
                idDetalleCotizacion = em.merge(idDetalleCotizacion);
            }
            Factura idFactura = detallefactura.getIdFactura();
            if (idFactura != null) {
                idFactura.getDetallefacturaList().remove(detallefactura);
                idFactura = em.merge(idFactura);
            }
            em.remove(detallefactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefactura> findDetallefacturaEntities() {
        return findDetallefacturaEntities(true, -1, -1);
    }

    public List<Detallefactura> findDetallefacturaEntities(int maxResults, int firstResult) {
        return findDetallefacturaEntities(false, maxResults, firstResult);
    }

    private List<Detallefactura> findDetallefacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefactura.class));
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

     public List<Detallefactura> listadoxpedido(int idDetalleCotizacion) {
        EntityManager em = getEntityManager();
        List<Detallefactura> lista = new ArrayList();
        try {
            Query q = em.createQuery("SELECT d FROM Detallefactura d WHERE d.idDetalleCotizacion.idDetalleCotizacion = :idDetalleCotizacion").setParameter("idDetalleCotizacion",idDetalleCotizacion);
            lista = (List<Detallefactura>)q.getResultList();
            System.out.println("Listado por detalle pedido" + lista.size());

        } 
        finally {
            em.close();
        }
        return lista;
    }
    
    public Detallefactura findDetallefactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefactura> rt = cq.from(Detallefactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
