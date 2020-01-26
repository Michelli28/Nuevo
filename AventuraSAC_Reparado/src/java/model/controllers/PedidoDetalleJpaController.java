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
import model.entities.Fichatecnica;
import model.entities.Pedido;
import model.entities.CotizacionDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.PedidoDetalle;

/**
 *
 * @author CHELLI BONITA
 */
public class PedidoDetalleJpaController implements Serializable {

    public PedidoDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PedidoDetalle pedidoDetalle) {
        if (pedidoDetalle.getCotizacionDetalleList() == null) {
            pedidoDetalle.setCotizacionDetalleList(new ArrayList<CotizacionDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fichatecnica idFicha = pedidoDetalle.getIdFicha();
            if (idFicha != null) {
                idFicha = em.getReference(idFicha.getClass(), idFicha.getIdFicha());
                pedidoDetalle.setIdFicha(idFicha);
            }
            Pedido idPedido = pedidoDetalle.getIdPedido();
            if (idPedido != null) {
                idPedido = em.getReference(idPedido.getClass(), idPedido.getIdPedido());
                pedidoDetalle.setIdPedido(idPedido);
            }
            List<CotizacionDetalle> attachedCotizacionDetalleList = new ArrayList<CotizacionDetalle>();
            for (CotizacionDetalle cotizacionDetalleListCotizacionDetalleToAttach : pedidoDetalle.getCotizacionDetalleList()) {
                cotizacionDetalleListCotizacionDetalleToAttach = em.getReference(cotizacionDetalleListCotizacionDetalleToAttach.getClass(), cotizacionDetalleListCotizacionDetalleToAttach.getIdDetalleCotizacion());
                attachedCotizacionDetalleList.add(cotizacionDetalleListCotizacionDetalleToAttach);
            }
            pedidoDetalle.setCotizacionDetalleList(attachedCotizacionDetalleList);
            em.persist(pedidoDetalle);
            if (idFicha != null) {
                idFicha.getPedidoDetalleList().add(pedidoDetalle);
                idFicha = em.merge(idFicha);
            }
            if (idPedido != null) {
                idPedido.getPedidoDetalleList().add(pedidoDetalle);
                idPedido = em.merge(idPedido);
            }
            for (CotizacionDetalle cotizacionDetalleListCotizacionDetalle : pedidoDetalle.getCotizacionDetalleList()) {
                PedidoDetalle oldIdDetallePedidoOfCotizacionDetalleListCotizacionDetalle = cotizacionDetalleListCotizacionDetalle.getIdDetallePedido();
                cotizacionDetalleListCotizacionDetalle.setIdDetallePedido(pedidoDetalle);
                cotizacionDetalleListCotizacionDetalle = em.merge(cotizacionDetalleListCotizacionDetalle);
                if (oldIdDetallePedidoOfCotizacionDetalleListCotizacionDetalle != null) {
                    oldIdDetallePedidoOfCotizacionDetalleListCotizacionDetalle.getCotizacionDetalleList().remove(cotizacionDetalleListCotizacionDetalle);
                    oldIdDetallePedidoOfCotizacionDetalleListCotizacionDetalle = em.merge(oldIdDetallePedidoOfCotizacionDetalleListCotizacionDetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PedidoDetalle pedidoDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoDetalle persistentPedidoDetalle = em.find(PedidoDetalle.class, pedidoDetalle.getIdDetallePedido());
            Fichatecnica idFichaOld = persistentPedidoDetalle.getIdFicha();
            Fichatecnica idFichaNew = pedidoDetalle.getIdFicha();
            Pedido idPedidoOld = persistentPedidoDetalle.getIdPedido();
            Pedido idPedidoNew = pedidoDetalle.getIdPedido();
            List<CotizacionDetalle> cotizacionDetalleListOld = persistentPedidoDetalle.getCotizacionDetalleList();
            List<CotizacionDetalle> cotizacionDetalleListNew = pedidoDetalle.getCotizacionDetalleList();
            if (idFichaNew != null) {
                idFichaNew = em.getReference(idFichaNew.getClass(), idFichaNew.getIdFicha());
                pedidoDetalle.setIdFicha(idFichaNew);
            }
            if (idPedidoNew != null) {
                idPedidoNew = em.getReference(idPedidoNew.getClass(), idPedidoNew.getIdPedido());
                pedidoDetalle.setIdPedido(idPedidoNew);
            }
            List<CotizacionDetalle> attachedCotizacionDetalleListNew = new ArrayList<CotizacionDetalle>();
            for (CotizacionDetalle cotizacionDetalleListNewCotizacionDetalleToAttach : cotizacionDetalleListNew) {
                cotizacionDetalleListNewCotizacionDetalleToAttach = em.getReference(cotizacionDetalleListNewCotizacionDetalleToAttach.getClass(), cotizacionDetalleListNewCotizacionDetalleToAttach.getIdDetalleCotizacion());
                attachedCotizacionDetalleListNew.add(cotizacionDetalleListNewCotizacionDetalleToAttach);
            }
            cotizacionDetalleListNew = attachedCotizacionDetalleListNew;
            pedidoDetalle.setCotizacionDetalleList(cotizacionDetalleListNew);
            pedidoDetalle = em.merge(pedidoDetalle);
            if (idFichaOld != null && !idFichaOld.equals(idFichaNew)) {
                idFichaOld.getPedidoDetalleList().remove(pedidoDetalle);
                idFichaOld = em.merge(idFichaOld);
            }
            if (idFichaNew != null && !idFichaNew.equals(idFichaOld)) {
                idFichaNew.getPedidoDetalleList().add(pedidoDetalle);
                idFichaNew = em.merge(idFichaNew);
            }
            if (idPedidoOld != null && !idPedidoOld.equals(idPedidoNew)) {
                idPedidoOld.getPedidoDetalleList().remove(pedidoDetalle);
                idPedidoOld = em.merge(idPedidoOld);
            }
            if (idPedidoNew != null && !idPedidoNew.equals(idPedidoOld)) {
                idPedidoNew.getPedidoDetalleList().add(pedidoDetalle);
                idPedidoNew = em.merge(idPedidoNew);
            }
            for (CotizacionDetalle cotizacionDetalleListOldCotizacionDetalle : cotizacionDetalleListOld) {
                if (!cotizacionDetalleListNew.contains(cotizacionDetalleListOldCotizacionDetalle)) {
                    cotizacionDetalleListOldCotizacionDetalle.setIdDetallePedido(null);
                    cotizacionDetalleListOldCotizacionDetalle = em.merge(cotizacionDetalleListOldCotizacionDetalle);
                }
            }
            for (CotizacionDetalle cotizacionDetalleListNewCotizacionDetalle : cotizacionDetalleListNew) {
                if (!cotizacionDetalleListOld.contains(cotizacionDetalleListNewCotizacionDetalle)) {
                    PedidoDetalle oldIdDetallePedidoOfCotizacionDetalleListNewCotizacionDetalle = cotizacionDetalleListNewCotizacionDetalle.getIdDetallePedido();
                    cotizacionDetalleListNewCotizacionDetalle.setIdDetallePedido(pedidoDetalle);
                    cotizacionDetalleListNewCotizacionDetalle = em.merge(cotizacionDetalleListNewCotizacionDetalle);
                    if (oldIdDetallePedidoOfCotizacionDetalleListNewCotizacionDetalle != null && !oldIdDetallePedidoOfCotizacionDetalleListNewCotizacionDetalle.equals(pedidoDetalle)) {
                        oldIdDetallePedidoOfCotizacionDetalleListNewCotizacionDetalle.getCotizacionDetalleList().remove(cotizacionDetalleListNewCotizacionDetalle);
                        oldIdDetallePedidoOfCotizacionDetalleListNewCotizacionDetalle = em.merge(oldIdDetallePedidoOfCotizacionDetalleListNewCotizacionDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidoDetalle.getIdDetallePedido();
                if (findPedidoDetalle(id) == null) {
                    throw new NonexistentEntityException("The pedidoDetalle with id " + id + " no longer exists.");
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
            PedidoDetalle pedidoDetalle;
            try {
                pedidoDetalle = em.getReference(PedidoDetalle.class, id);
                pedidoDetalle.getIdDetallePedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidoDetalle with id " + id + " no longer exists.", enfe);
            }
            Fichatecnica idFicha = pedidoDetalle.getIdFicha();
            if (idFicha != null) {
                idFicha.getPedidoDetalleList().remove(pedidoDetalle);
                idFicha = em.merge(idFicha);
            }
            Pedido idPedido = pedidoDetalle.getIdPedido();
            if (idPedido != null) {
                idPedido.getPedidoDetalleList().remove(pedidoDetalle);
                idPedido = em.merge(idPedido);
            }
            List<CotizacionDetalle> cotizacionDetalleList = pedidoDetalle.getCotizacionDetalleList();
            for (CotizacionDetalle cotizacionDetalleListCotizacionDetalle : cotizacionDetalleList) {
                cotizacionDetalleListCotizacionDetalle.setIdDetallePedido(null);
                cotizacionDetalleListCotizacionDetalle = em.merge(cotizacionDetalleListCotizacionDetalle);
            }
            em.remove(pedidoDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PedidoDetalle> findPedidoDetalleEntities() {
        return findPedidoDetalleEntities(true, -1, -1);
    }

    public List<PedidoDetalle> findPedidoDetalleEntities(int maxResults, int firstResult) {
        return findPedidoDetalleEntities(false, maxResults, firstResult);
    }

    private List<PedidoDetalle> findPedidoDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PedidoDetalle.class));
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

     public List<PedidoDetalle> listadoxficha(int idFicha) {
        EntityManager em = getEntityManager();
        List<PedidoDetalle> lista = new ArrayList();
        try {
            Query q = em.createQuery("SELECT p FROM PedidoDetalle p WHERE p.idFicha.idFicha = :idFicha").setParameter("idFicha",idFicha);
            lista = (List<PedidoDetalle>)q.getResultList();
            System.out.println("Listado por ficha" + lista.size());
            
        //}catch(Exception e){
            
          //  System.out.println(e.getMessage());
        } 
        finally {
            em.close();
        }
        return lista;
    }

     
    public PedidoDetalle findPedidoDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedidoDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PedidoDetalle> rt = cq.from(PedidoDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
