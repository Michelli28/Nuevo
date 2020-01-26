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
import model.entities.PedidoDetalle;
import model.entities.Cotizacion;
import model.entities.Detallefactura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.CotizacionDetalle;

/**
 *
 * @author CHELLI BONITA
 */
public class CotizacionDetalleJpaController implements Serializable {

    public CotizacionDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CotizacionDetalle cotizacionDetalle) {
        if (cotizacionDetalle.getDetallefacturaList() == null) {
            cotizacionDetalle.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoDetalle idDetallePedido = cotizacionDetalle.getIdDetallePedido();
            if (idDetallePedido != null) {
                idDetallePedido = em.getReference(idDetallePedido.getClass(), idDetallePedido.getIdDetallePedido());
                cotizacionDetalle.setIdDetallePedido(idDetallePedido);
            }
            Cotizacion idCotizacion = cotizacionDetalle.getIdCotizacion();
            if (idCotizacion != null) {
                idCotizacion = em.getReference(idCotizacion.getClass(), idCotizacion.getIdCotizacion());
                cotizacionDetalle.setIdCotizacion(idCotizacion);
            }
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : cotizacionDetalle.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getIdDetalleFactura());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            cotizacionDetalle.setDetallefacturaList(attachedDetallefacturaList);
            em.persist(cotizacionDetalle);
            if (idDetallePedido != null) {
                idDetallePedido.getCotizacionDetalleList().add(cotizacionDetalle);
                idDetallePedido = em.merge(idDetallePedido);
            }
            if (idCotizacion != null) {
                idCotizacion.getCotizacionDetalleList().add(cotizacionDetalle);
                idCotizacion = em.merge(idCotizacion);
            }
            for (Detallefactura detallefacturaListDetallefactura : cotizacionDetalle.getDetallefacturaList()) {
                CotizacionDetalle oldIdDetalleCotizacionOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getIdDetalleCotizacion();
                detallefacturaListDetallefactura.setIdDetalleCotizacion(cotizacionDetalle);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldIdDetalleCotizacionOfDetallefacturaListDetallefactura != null) {
                    oldIdDetalleCotizacionOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldIdDetalleCotizacionOfDetallefacturaListDetallefactura = em.merge(oldIdDetalleCotizacionOfDetallefacturaListDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CotizacionDetalle cotizacionDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CotizacionDetalle persistentCotizacionDetalle = em.find(CotizacionDetalle.class, cotizacionDetalle.getIdDetalleCotizacion());
            PedidoDetalle idDetallePedidoOld = persistentCotizacionDetalle.getIdDetallePedido();
            PedidoDetalle idDetallePedidoNew = cotizacionDetalle.getIdDetallePedido();
            Cotizacion idCotizacionOld = persistentCotizacionDetalle.getIdCotizacion();
            Cotizacion idCotizacionNew = cotizacionDetalle.getIdCotizacion();
            List<Detallefactura> detallefacturaListOld = persistentCotizacionDetalle.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = cotizacionDetalle.getDetallefacturaList();
            if (idDetallePedidoNew != null) {
                idDetallePedidoNew = em.getReference(idDetallePedidoNew.getClass(), idDetallePedidoNew.getIdDetallePedido());
                cotizacionDetalle.setIdDetallePedido(idDetallePedidoNew);
            }
            if (idCotizacionNew != null) {
                idCotizacionNew = em.getReference(idCotizacionNew.getClass(), idCotizacionNew.getIdCotizacion());
                cotizacionDetalle.setIdCotizacion(idCotizacionNew);
            }
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getIdDetalleFactura());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            cotizacionDetalle.setDetallefacturaList(detallefacturaListNew);
            cotizacionDetalle = em.merge(cotizacionDetalle);
            if (idDetallePedidoOld != null && !idDetallePedidoOld.equals(idDetallePedidoNew)) {
                idDetallePedidoOld.getCotizacionDetalleList().remove(cotizacionDetalle);
                idDetallePedidoOld = em.merge(idDetallePedidoOld);
            }
            if (idDetallePedidoNew != null && !idDetallePedidoNew.equals(idDetallePedidoOld)) {
                idDetallePedidoNew.getCotizacionDetalleList().add(cotizacionDetalle);
                idDetallePedidoNew = em.merge(idDetallePedidoNew);
            }
            if (idCotizacionOld != null && !idCotizacionOld.equals(idCotizacionNew)) {
                idCotizacionOld.getCotizacionDetalleList().remove(cotizacionDetalle);
                idCotizacionOld = em.merge(idCotizacionOld);
            }
            if (idCotizacionNew != null && !idCotizacionNew.equals(idCotizacionOld)) {
                idCotizacionNew.getCotizacionDetalleList().add(cotizacionDetalle);
                idCotizacionNew = em.merge(idCotizacionNew);
            }
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    detallefacturaListOldDetallefactura.setIdDetalleCotizacion(null);
                    detallefacturaListOldDetallefactura = em.merge(detallefacturaListOldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    CotizacionDetalle oldIdDetalleCotizacionOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getIdDetalleCotizacion();
                    detallefacturaListNewDetallefactura.setIdDetalleCotizacion(cotizacionDetalle);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldIdDetalleCotizacionOfDetallefacturaListNewDetallefactura != null && !oldIdDetalleCotizacionOfDetallefacturaListNewDetallefactura.equals(cotizacionDetalle)) {
                        oldIdDetalleCotizacionOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldIdDetalleCotizacionOfDetallefacturaListNewDetallefactura = em.merge(oldIdDetalleCotizacionOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cotizacionDetalle.getIdDetalleCotizacion();
                if (findCotizacionDetalle(id) == null) {
                    throw new NonexistentEntityException("The cotizacionDetalle with id " + id + " no longer exists.");
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
            CotizacionDetalle cotizacionDetalle;
            try {
                cotizacionDetalle = em.getReference(CotizacionDetalle.class, id);
                cotizacionDetalle.getIdDetalleCotizacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cotizacionDetalle with id " + id + " no longer exists.", enfe);
            }
            PedidoDetalle idDetallePedido = cotizacionDetalle.getIdDetallePedido();
            if (idDetallePedido != null) {
                idDetallePedido.getCotizacionDetalleList().remove(cotizacionDetalle);
                idDetallePedido = em.merge(idDetallePedido);
            }
            Cotizacion idCotizacion = cotizacionDetalle.getIdCotizacion();
            if (idCotizacion != null) {
                idCotizacion.getCotizacionDetalleList().remove(cotizacionDetalle);
                idCotizacion = em.merge(idCotizacion);
            }
            List<Detallefactura> detallefacturaList = cotizacionDetalle.getDetallefacturaList();
            for (Detallefactura detallefacturaListDetallefactura : detallefacturaList) {
                detallefacturaListDetallefactura.setIdDetalleCotizacion(null);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
            }
            em.remove(cotizacionDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CotizacionDetalle> findCotizacionDetalleEntities() {
        return findCotizacionDetalleEntities(true, -1, -1);
    }

    public List<CotizacionDetalle> findCotizacionDetalleEntities(int maxResults, int firstResult) {
        return findCotizacionDetalleEntities(false, maxResults, firstResult);
    }

    private List<CotizacionDetalle> findCotizacionDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CotizacionDetalle.class));
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
    
    public List<CotizacionDetalle> listadoxpedido(int idDetallePedido) {
        EntityManager em = getEntityManager();
        List<CotizacionDetalle> lista = new ArrayList();
        try {
            Query q = em.createQuery("SELECT c FROM CotizacionDetalle c WHERE c.idDetallePedido.idDetallePedido = :idDetallePedido").setParameter("idDetallePedido",idDetallePedido);
            lista = (List<CotizacionDetalle>)q.getResultList();
            System.out.println("Listado por detalle pedido" + lista.size());

        } 
        finally {
            em.close();
        }
        return lista;
    }
    
    public List<CotizacionDetalle> listadoxdetallecot(int idCotizacion) {
        EntityManager em = getEntityManager();
        List<CotizacionDetalle> lista = new ArrayList();
        try {
            Query q = em.createQuery("SELECT c FROM CotizacionDetalle c WHERE c.idCotizacion.idCotizacion = :idCotizacion").setParameter("idCotizacion",idCotizacion);
            lista = (List<CotizacionDetalle>)q.getResultList();
            System.out.println("Listado por cotizacion" + lista.size());

        } 
        finally {
            em.close();
        }
        return lista;
    }



    public CotizacionDetalle findCotizacionDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CotizacionDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getCotizacionDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CotizacionDetalle> rt = cq.from(CotizacionDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
