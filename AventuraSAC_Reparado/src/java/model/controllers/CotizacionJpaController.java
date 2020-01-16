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
import model.entities.CotizacionDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Cotizacion;

/**
 *
 * @author CHELLI BONITA
 */
public class CotizacionJpaController implements Serializable {

    public CotizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cotizacion cotizacion) {
        if (cotizacion.getCotizacionDetalleList() == null) {
            cotizacion.setCotizacionDetalleList(new ArrayList<CotizacionDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido idPedido = cotizacion.getIdPedido();
            if (idPedido != null) {
                idPedido = em.getReference(idPedido.getClass(), idPedido.getIdPedido());
                cotizacion.setIdPedido(idPedido);
            }
//            List<CotizacionDetalle> attachedCotizacionDetalleList = new ArrayList<CotizacionDetalle>();
//
//            for (CotizacionDetalle cotizacionDetalleListCotizacionDetalleToAttach : cotizacion.getCotizacionDetalleList()) {
//                if (cotizacionDetalleListCotizacionDetalleToAttach.getIdDetalleCotizacion() != null) {
//                    //cotizacionDetalleListCotizacionDetalleToAttach = em.getReference(cotizacionDetalleListCotizacionDetalleToAttach.getClass(), cotizacionDetalleListCotizacionDetalleToAttach.getIdDetalleCotizacion());
//                    attachedCotizacionDetalleList.add(cotizacionDetalleListCotizacionDetalleToAttach);
//                }
//            }
//
//            cotizacion.setCotizacionDetalleList(attachedCotizacionDetalleList);
            em.persist(cotizacion);
            if (idPedido != null) {
                idPedido.getCotizacionList().add(cotizacion);
                idPedido = em.merge(idPedido);
            }
            if (cotizacion.getCotizacionDetalleList() != null) {
                for (CotizacionDetalle cotizacionDetalleListCotizacionDetalle : cotizacion.getCotizacionDetalleList()) {
//                    Cotizacion oldIdCotizacionOfCotizacionDetalleListCotizacionDetalle = cotizacionDetalleListCotizacionDetalle.getIdCotizacion();
//                    cotizacionDetalleListCotizacionDetalle.setIdCotizacion(cotizacion);
                    if (cotizacionDetalleListCotizacionDetalle.getIdDetalleCotizacion() == 0) {
                        em.persist(cotizacionDetalleListCotizacionDetalle);
                    }
                    else
                        em.merge(cotizacionDetalleListCotizacionDetalle);
//                    if (oldIdCotizacionOfCotizacionDetalleListCotizacionDetalle != null) {
//                        oldIdCotizacionOfCotizacionDetalleListCotizacionDetalle.getCotizacionDetalleList().remove(cotizacionDetalleListCotizacionDetalle);
//                        oldIdCotizacionOfCotizacionDetalleListCotizacionDetalle = em.merge(oldIdCotizacionOfCotizacionDetalleListCotizacionDetalle);
//                    }
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cotizacion cotizacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotizacion persistentCotizacion = em.find(Cotizacion.class, cotizacion.getIdCotizacion());
            Pedido idPedidoOld = persistentCotizacion.getIdPedido();
            Pedido idPedidoNew = cotizacion.getIdPedido();
            List<CotizacionDetalle> cotizacionDetalleListOld = persistentCotizacion.getCotizacionDetalleList();
            List<CotizacionDetalle> cotizacionDetalleListNew = cotizacion.getCotizacionDetalleList();
            if (idPedidoNew != null) {
                idPedidoNew = em.getReference(idPedidoNew.getClass(), idPedidoNew.getIdPedido());
                cotizacion.setIdPedido(idPedidoNew);
            }
            List<CotizacionDetalle> attachedCotizacionDetalleListNew = new ArrayList<CotizacionDetalle>();

            for (CotizacionDetalle cotizacionDetalleListNewCotizacionDetalleToAttach : cotizacionDetalleListNew) {
                cotizacionDetalleListNewCotizacionDetalleToAttach = em.getReference(cotizacionDetalleListNewCotizacionDetalleToAttach.getClass(), cotizacionDetalleListNewCotizacionDetalleToAttach.getIdDetalleCotizacion());
                attachedCotizacionDetalleListNew.add(cotizacionDetalleListNewCotizacionDetalleToAttach);
            }

            cotizacionDetalleListNew = attachedCotizacionDetalleListNew;
            cotizacion.setCotizacionDetalleList(cotizacionDetalleListNew);
            cotizacion = em.merge(cotizacion);
            if (idPedidoOld != null && !idPedidoOld.equals(idPedidoNew)) {
                idPedidoOld.getCotizacionList().remove(cotizacion);
                idPedidoOld = em.merge(idPedidoOld);
            }
            if (idPedidoNew != null && !idPedidoNew.equals(idPedidoOld)) {
                idPedidoNew.getCotizacionList().add(cotizacion);
                idPedidoNew = em.merge(idPedidoNew);
            }
            for (CotizacionDetalle cotizacionDetalleListOldCotizacionDetalle : cotizacionDetalleListOld) {
                if (!cotizacionDetalleListNew.contains(cotizacionDetalleListOldCotizacionDetalle)) {
                    cotizacionDetalleListOldCotizacionDetalle.setIdCotizacion(null);
                    cotizacionDetalleListOldCotizacionDetalle = em.merge(cotizacionDetalleListOldCotizacionDetalle);
                }
            }
            for (CotizacionDetalle cotizacionDetalleListNewCotizacionDetalle : cotizacionDetalleListNew) {
                if (!cotizacionDetalleListOld.contains(cotizacionDetalleListNewCotizacionDetalle)) {
                    Cotizacion oldIdCotizacionOfCotizacionDetalleListNewCotizacionDetalle = cotizacionDetalleListNewCotizacionDetalle.getIdCotizacion();
                    cotizacionDetalleListNewCotizacionDetalle.setIdCotizacion(cotizacion);
                    cotizacionDetalleListNewCotizacionDetalle = em.merge(cotizacionDetalleListNewCotizacionDetalle);
                    if (oldIdCotizacionOfCotizacionDetalleListNewCotizacionDetalle != null && !oldIdCotizacionOfCotizacionDetalleListNewCotizacionDetalle.equals(cotizacion)) {
                        oldIdCotizacionOfCotizacionDetalleListNewCotizacionDetalle.getCotizacionDetalleList().remove(cotizacionDetalleListNewCotizacionDetalle);
                        oldIdCotizacionOfCotizacionDetalleListNewCotizacionDetalle = em.merge(oldIdCotizacionOfCotizacionDetalleListNewCotizacionDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cotizacion.getIdCotizacion();
                if (findCotizacion(id) == null) {
                    throw new NonexistentEntityException("The cotizacion with id " + id + " no longer exists.");
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
            Cotizacion cotizacion;
            try {
                cotizacion = em.getReference(Cotizacion.class, id);
                cotizacion.getIdCotizacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cotizacion with id " + id + " no longer exists.", enfe);
            }
            Pedido idPedido = cotizacion.getIdPedido();
            if (idPedido != null) {
                idPedido.getCotizacionList().remove(cotizacion);
                idPedido = em.merge(idPedido);
            }
            List<CotizacionDetalle> cotizacionDetalleList = cotizacion.getCotizacionDetalleList();
            for (CotizacionDetalle cotizacionDetalleListCotizacionDetalle : cotizacionDetalleList) {
                cotizacionDetalleListCotizacionDetalle.setIdCotizacion(null);
                cotizacionDetalleListCotizacionDetalle = em.merge(cotizacionDetalleListCotizacionDetalle);
            }
            em.remove(cotizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cotizacion> findCotizacionEntities() {
        return findCotizacionEntities(true, -1, -1);
    }

    public List<Cotizacion> findCotizacionEntities(int maxResults, int firstResult) {
        return findCotizacionEntities(false, maxResults, firstResult);
    }

    private List<Cotizacion> findCotizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cotizacion.class));
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

    public Cotizacion findCotizacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cotizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCotizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cotizacion> rt = cq.from(Cotizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
