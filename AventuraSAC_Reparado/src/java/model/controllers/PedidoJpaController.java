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
import model.entities.Estadopedido;
import model.entities.Cliente;
import model.entities.PedidoDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Ordencompra;
import model.entities.GuiaremisionDetalle;
import model.entities.Pagos;
import model.entities.Cotizacion;
import model.entities.Pedido;

/**
 *
 * @author CHELLI BONITA
 */
public class PedidoJpaController implements Serializable {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        if (pedido.getPedidoDetalleList() == null) {
            pedido.setPedidoDetalleList(new ArrayList<PedidoDetalle>());
        }
        if (pedido.getOrdencompraList() == null) {
            pedido.setOrdencompraList(new ArrayList<Ordencompra>());
        }
        if (pedido.getGuiaremisionDetalleList() == null) {
            pedido.setGuiaremisionDetalleList(new ArrayList<GuiaremisionDetalle>());
        }
        if (pedido.getPagosList() == null) {
            pedido.setPagosList(new ArrayList<Pagos>());
        }
        if (pedido.getCotizacionList() == null) {
            pedido.setCotizacionList(new ArrayList<Cotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopedido idEstado = pedido.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                pedido.setIdEstado(idEstado);
            }
            Cliente idCliente = pedido.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                pedido.setIdCliente(idCliente);
            }
            List<PedidoDetalle> attachedPedidoDetalleList = new ArrayList<PedidoDetalle>();
            for (PedidoDetalle pedidoDetalleListPedidoDetalleToAttach : pedido.getPedidoDetalleList()) {
                if (pedidoDetalleListPedidoDetalleToAttach.getIdPedido() != null) {
                    //pedidoDetalleListPedidoDetalleToAttach = em.getReference(pedidoDetalleListPedidoDetalleToAttach.getClass(), pedidoDetalleListPedidoDetalleToAttach.getIdDetallePedido());
                    attachedPedidoDetalleList.add(pedidoDetalleListPedidoDetalleToAttach);
                }
            }
            pedido.setPedidoDetalleList(attachedPedidoDetalleList);
            List<Ordencompra> attachedOrdencompraList = new ArrayList<Ordencompra>();
            for (Ordencompra ordencompraListOrdencompraToAttach : pedido.getOrdencompraList()) {
                if (ordencompraListOrdencompraToAttach.getIdOrdenCompra() != null) {
                    //ordencompraListOrdencompraToAttach = em.getReference(ordencompraListOrdencompraToAttach.getClass(), ordencompraListOrdencompraToAttach.getIdOrdenCompra());

                    attachedOrdencompraList.add(ordencompraListOrdencompraToAttach);
                }
            }
            pedido.setOrdencompraList(attachedOrdencompraList);
            List<GuiaremisionDetalle> attachedGuiaremisionDetalleList = new ArrayList<GuiaremisionDetalle>();
            for (GuiaremisionDetalle guiaremisionDetalleListGuiaremisionDetalleToAttach : pedido.getGuiaremisionDetalleList()) {
                if (guiaremisionDetalleListGuiaremisionDetalleToAttach.getIdDetalleGuiaRemision() != null) {
                    //guiaremisionDetalleListGuiaremisionDetalleToAttach = em.getReference(guiaremisionDetalleListGuiaremisionDetalleToAttach.getClass(), guiaremisionDetalleListGuiaremisionDetalleToAttach.getIdDetalleGuiaRemision());
                    attachedGuiaremisionDetalleList.add(guiaremisionDetalleListGuiaremisionDetalleToAttach);
                }
            }
            pedido.setGuiaremisionDetalleList(attachedGuiaremisionDetalleList);
            List<Pagos> attachedPagosList = new ArrayList<Pagos>();
            for (Pagos pagosListPagosToAttach : pedido.getPagosList()) {
                if (pagosListPagosToAttach.getNumeroOperacion() != null) {
                    //pagosListPagosToAttach = em.getReference(pagosListPagosToAttach.getClass(), pagosListPagosToAttach.getNumeroOperacion());
                    attachedPagosList.add(pagosListPagosToAttach);
                }
            }
            pedido.setPagosList(attachedPagosList);
            List<Cotizacion> attachedCotizacionList = new ArrayList<Cotizacion>();
            for (Cotizacion cotizacionListCotizacionToAttach : pedido.getCotizacionList()) {
                if (cotizacionListCotizacionToAttach.getIdCotizacion() != null) {
                    //cotizacionListCotizacionToAttach = em.getReference(cotizacionListCotizacionToAttach.getClass(), cotizacionListCotizacionToAttach.getIdCotizacion());
                    attachedCotizacionList.add(cotizacionListCotizacionToAttach);
                }
            }
            pedido.setCotizacionList(attachedCotizacionList);
            em.persist(pedido);
            if (idEstado != null) {
                idEstado.getPedidoList().add(pedido);
                idEstado = em.merge(idEstado);
            }
            if (idCliente != null) {
                idCliente.getPedidoList().add(pedido);
                idCliente = em.merge(idCliente);
            }
            if (pedido.getPedidoDetalleList() == null) {
            for (PedidoDetalle pedidoDetalleListPedidoDetalle : pedido.getPedidoDetalleList()) {
                Pedido oldIdPedidoOfPedidoDetalleListPedidoDetalle = pedidoDetalleListPedidoDetalle.getIdPedido();
                pedidoDetalleListPedidoDetalle.setIdPedido(pedido);
                pedidoDetalleListPedidoDetalle = em.merge(pedidoDetalleListPedidoDetalle);
                if (oldIdPedidoOfPedidoDetalleListPedidoDetalle != null) {
                    oldIdPedidoOfPedidoDetalleListPedidoDetalle.getPedidoDetalleList().remove(pedidoDetalleListPedidoDetalle);
                    oldIdPedidoOfPedidoDetalleListPedidoDetalle = em.merge(oldIdPedidoOfPedidoDetalleListPedidoDetalle);
                }
            }}
            for (Ordencompra ordencompraListOrdencompra : pedido.getOrdencompraList()) {
                Pedido oldIdPedidoOfOrdencompraListOrdencompra = ordencompraListOrdencompra.getIdPedido();
                ordencompraListOrdencompra.setIdPedido(pedido);
                ordencompraListOrdencompra = em.merge(ordencompraListOrdencompra);
                if (oldIdPedidoOfOrdencompraListOrdencompra != null) {
                    oldIdPedidoOfOrdencompraListOrdencompra.getOrdencompraList().remove(ordencompraListOrdencompra);
                    oldIdPedidoOfOrdencompraListOrdencompra = em.merge(oldIdPedidoOfOrdencompraListOrdencompra);
                }
            }
            for (GuiaremisionDetalle guiaremisionDetalleListGuiaremisionDetalle : pedido.getGuiaremisionDetalleList()) {
                Pedido oldIdPedidoOfGuiaremisionDetalleListGuiaremisionDetalle = guiaremisionDetalleListGuiaremisionDetalle.getIdPedido();
                guiaremisionDetalleListGuiaremisionDetalle.setIdPedido(pedido);
                guiaremisionDetalleListGuiaremisionDetalle = em.merge(guiaremisionDetalleListGuiaremisionDetalle);
                if (oldIdPedidoOfGuiaremisionDetalleListGuiaremisionDetalle != null) {
                    oldIdPedidoOfGuiaremisionDetalleListGuiaremisionDetalle.getGuiaremisionDetalleList().remove(guiaremisionDetalleListGuiaremisionDetalle);
                    oldIdPedidoOfGuiaremisionDetalleListGuiaremisionDetalle = em.merge(oldIdPedidoOfGuiaremisionDetalleListGuiaremisionDetalle);
                }
            }
            for (Pagos pagosListPagos : pedido.getPagosList()) {
                Pedido oldIdPedidoOfPagosListPagos = pagosListPagos.getIdPedido();
                pagosListPagos.setIdPedido(pedido);
                pagosListPagos = em.merge(pagosListPagos);
                if (oldIdPedidoOfPagosListPagos != null) {
                    oldIdPedidoOfPagosListPagos.getPagosList().remove(pagosListPagos);
                    oldIdPedidoOfPagosListPagos = em.merge(oldIdPedidoOfPagosListPagos);
                }
            }
            for (Cotizacion cotizacionListCotizacion : pedido.getCotizacionList()) {
                Pedido oldIdPedidoOfCotizacionListCotizacion = cotizacionListCotizacion.getIdPedido();
                cotizacionListCotizacion.setIdPedido(pedido);
                cotizacionListCotizacion = em.merge(cotizacionListCotizacion);
                if (oldIdPedidoOfCotizacionListCotizacion != null) {
                    oldIdPedidoOfCotizacionListCotizacion.getCotizacionList().remove(cotizacionListCotizacion);
                    oldIdPedidoOfCotizacionListCotizacion = em.merge(oldIdPedidoOfCotizacionListCotizacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getIdPedido());
            Estadopedido idEstadoOld = persistentPedido.getIdEstado();
            Estadopedido idEstadoNew = pedido.getIdEstado();
            Cliente idClienteOld = persistentPedido.getIdCliente();
            Cliente idClienteNew = pedido.getIdCliente();
            List<PedidoDetalle> pedidoDetalleListOld = persistentPedido.getPedidoDetalleList();
            List<PedidoDetalle> pedidoDetalleListNew = pedido.getPedidoDetalleList();
            List<Ordencompra> ordencompraListOld = persistentPedido.getOrdencompraList();
            List<Ordencompra> ordencompraListNew = pedido.getOrdencompraList();
            List<GuiaremisionDetalle> guiaremisionDetalleListOld = persistentPedido.getGuiaremisionDetalleList();
            List<GuiaremisionDetalle> guiaremisionDetalleListNew = pedido.getGuiaremisionDetalleList();
            List<Pagos> pagosListOld = persistentPedido.getPagosList();
            List<Pagos> pagosListNew = pedido.getPagosList();
            List<Cotizacion> cotizacionListOld = persistentPedido.getCotizacionList();
            List<Cotizacion> cotizacionListNew = pedido.getCotizacionList();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                pedido.setIdEstado(idEstadoNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                pedido.setIdCliente(idClienteNew);
            }
            List<PedidoDetalle> attachedPedidoDetalleListNew = new ArrayList<PedidoDetalle>();
            for (PedidoDetalle pedidoDetalleListNewPedidoDetalleToAttach : pedidoDetalleListNew) {
                pedidoDetalleListNewPedidoDetalleToAttach = em.getReference(pedidoDetalleListNewPedidoDetalleToAttach.getClass(), pedidoDetalleListNewPedidoDetalleToAttach.getIdDetallePedido());
                attachedPedidoDetalleListNew.add(pedidoDetalleListNewPedidoDetalleToAttach);
            }
            pedidoDetalleListNew = attachedPedidoDetalleListNew;
            pedido.setPedidoDetalleList(pedidoDetalleListNew);
            List<Ordencompra> attachedOrdencompraListNew = new ArrayList<Ordencompra>();
            for (Ordencompra ordencompraListNewOrdencompraToAttach : ordencompraListNew) {
                ordencompraListNewOrdencompraToAttach = em.getReference(ordencompraListNewOrdencompraToAttach.getClass(), ordencompraListNewOrdencompraToAttach.getIdOrdenCompra());
                attachedOrdencompraListNew.add(ordencompraListNewOrdencompraToAttach);
            }
            ordencompraListNew = attachedOrdencompraListNew;
            pedido.setOrdencompraList(ordencompraListNew);
            List<GuiaremisionDetalle> attachedGuiaremisionDetalleListNew = new ArrayList<GuiaremisionDetalle>();
            for (GuiaremisionDetalle guiaremisionDetalleListNewGuiaremisionDetalleToAttach : guiaremisionDetalleListNew) {
                guiaremisionDetalleListNewGuiaremisionDetalleToAttach = em.getReference(guiaremisionDetalleListNewGuiaremisionDetalleToAttach.getClass(), guiaremisionDetalleListNewGuiaremisionDetalleToAttach.getIdDetalleGuiaRemision());
                attachedGuiaremisionDetalleListNew.add(guiaremisionDetalleListNewGuiaremisionDetalleToAttach);
            }
            guiaremisionDetalleListNew = attachedGuiaremisionDetalleListNew;
            pedido.setGuiaremisionDetalleList(guiaremisionDetalleListNew);
            List<Pagos> attachedPagosListNew = new ArrayList<Pagos>();
            for (Pagos pagosListNewPagosToAttach : pagosListNew) {
                pagosListNewPagosToAttach = em.getReference(pagosListNewPagosToAttach.getClass(), pagosListNewPagosToAttach.getNumeroOperacion());
                attachedPagosListNew.add(pagosListNewPagosToAttach);
            }
            pagosListNew = attachedPagosListNew;
            pedido.setPagosList(pagosListNew);
            List<Cotizacion> attachedCotizacionListNew = new ArrayList<Cotizacion>();
            for (Cotizacion cotizacionListNewCotizacionToAttach : cotizacionListNew) {
                cotizacionListNewCotizacionToAttach = em.getReference(cotizacionListNewCotizacionToAttach.getClass(), cotizacionListNewCotizacionToAttach.getIdCotizacion());
                attachedCotizacionListNew.add(cotizacionListNewCotizacionToAttach);
            }
            cotizacionListNew = attachedCotizacionListNew;
            pedido.setCotizacionList(cotizacionListNew);
            pedido = em.merge(pedido);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getPedidoList().remove(pedido);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getPedidoList().add(pedido);
                idEstadoNew = em.merge(idEstadoNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getPedidoList().remove(pedido);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getPedidoList().add(pedido);
                idClienteNew = em.merge(idClienteNew);
            }
            for (PedidoDetalle pedidoDetalleListOldPedidoDetalle : pedidoDetalleListOld) {
                if (!pedidoDetalleListNew.contains(pedidoDetalleListOldPedidoDetalle)) {
                    pedidoDetalleListOldPedidoDetalle.setIdPedido(null);
                    pedidoDetalleListOldPedidoDetalle = em.merge(pedidoDetalleListOldPedidoDetalle);
                }
            }
            for (PedidoDetalle pedidoDetalleListNewPedidoDetalle : pedidoDetalleListNew) {
                if (!pedidoDetalleListOld.contains(pedidoDetalleListNewPedidoDetalle)) {
                    Pedido oldIdPedidoOfPedidoDetalleListNewPedidoDetalle = pedidoDetalleListNewPedidoDetalle.getIdPedido();
                    pedidoDetalleListNewPedidoDetalle.setIdPedido(pedido);
                    pedidoDetalleListNewPedidoDetalle = em.merge(pedidoDetalleListNewPedidoDetalle);
                    if (oldIdPedidoOfPedidoDetalleListNewPedidoDetalle != null && !oldIdPedidoOfPedidoDetalleListNewPedidoDetalle.equals(pedido)) {
                        oldIdPedidoOfPedidoDetalleListNewPedidoDetalle.getPedidoDetalleList().remove(pedidoDetalleListNewPedidoDetalle);
                        oldIdPedidoOfPedidoDetalleListNewPedidoDetalle = em.merge(oldIdPedidoOfPedidoDetalleListNewPedidoDetalle);
                    }
                }
            }
            for (Ordencompra ordencompraListOldOrdencompra : ordencompraListOld) {
                if (!ordencompraListNew.contains(ordencompraListOldOrdencompra)) {
                    ordencompraListOldOrdencompra.setIdPedido(null);
                    ordencompraListOldOrdencompra = em.merge(ordencompraListOldOrdencompra);
                }
            }
            for (Ordencompra ordencompraListNewOrdencompra : ordencompraListNew) {
                if (!ordencompraListOld.contains(ordencompraListNewOrdencompra)) {
                    Pedido oldIdPedidoOfOrdencompraListNewOrdencompra = ordencompraListNewOrdencompra.getIdPedido();
                    ordencompraListNewOrdencompra.setIdPedido(pedido);
                    ordencompraListNewOrdencompra = em.merge(ordencompraListNewOrdencompra);
                    if (oldIdPedidoOfOrdencompraListNewOrdencompra != null && !oldIdPedidoOfOrdencompraListNewOrdencompra.equals(pedido)) {
                        oldIdPedidoOfOrdencompraListNewOrdencompra.getOrdencompraList().remove(ordencompraListNewOrdencompra);
                        oldIdPedidoOfOrdencompraListNewOrdencompra = em.merge(oldIdPedidoOfOrdencompraListNewOrdencompra);
                    }
                }
            }
            for (GuiaremisionDetalle guiaremisionDetalleListOldGuiaremisionDetalle : guiaremisionDetalleListOld) {
                if (!guiaremisionDetalleListNew.contains(guiaremisionDetalleListOldGuiaremisionDetalle)) {
                    guiaremisionDetalleListOldGuiaremisionDetalle.setIdPedido(null);
                    guiaremisionDetalleListOldGuiaremisionDetalle = em.merge(guiaremisionDetalleListOldGuiaremisionDetalle);
                }
            }
            for (GuiaremisionDetalle guiaremisionDetalleListNewGuiaremisionDetalle : guiaremisionDetalleListNew) {
                if (!guiaremisionDetalleListOld.contains(guiaremisionDetalleListNewGuiaremisionDetalle)) {
                    Pedido oldIdPedidoOfGuiaremisionDetalleListNewGuiaremisionDetalle = guiaremisionDetalleListNewGuiaremisionDetalle.getIdPedido();
                    guiaremisionDetalleListNewGuiaremisionDetalle.setIdPedido(pedido);
                    guiaremisionDetalleListNewGuiaremisionDetalle = em.merge(guiaremisionDetalleListNewGuiaremisionDetalle);
                    if (oldIdPedidoOfGuiaremisionDetalleListNewGuiaremisionDetalle != null && !oldIdPedidoOfGuiaremisionDetalleListNewGuiaremisionDetalle.equals(pedido)) {
                        oldIdPedidoOfGuiaremisionDetalleListNewGuiaremisionDetalle.getGuiaremisionDetalleList().remove(guiaremisionDetalleListNewGuiaremisionDetalle);
                        oldIdPedidoOfGuiaremisionDetalleListNewGuiaremisionDetalle = em.merge(oldIdPedidoOfGuiaremisionDetalleListNewGuiaremisionDetalle);
                    }
                }
            }
            for (Pagos pagosListOldPagos : pagosListOld) {
                if (!pagosListNew.contains(pagosListOldPagos)) {
                    pagosListOldPagos.setIdPedido(null);
                    pagosListOldPagos = em.merge(pagosListOldPagos);
                }
            }
            for (Pagos pagosListNewPagos : pagosListNew) {
                if (!pagosListOld.contains(pagosListNewPagos)) {
                    Pedido oldIdPedidoOfPagosListNewPagos = pagosListNewPagos.getIdPedido();
                    pagosListNewPagos.setIdPedido(pedido);
                    pagosListNewPagos = em.merge(pagosListNewPagos);
                    if (oldIdPedidoOfPagosListNewPagos != null && !oldIdPedidoOfPagosListNewPagos.equals(pedido)) {
                        oldIdPedidoOfPagosListNewPagos.getPagosList().remove(pagosListNewPagos);
                        oldIdPedidoOfPagosListNewPagos = em.merge(oldIdPedidoOfPagosListNewPagos);
                    }
                }
            }
            for (Cotizacion cotizacionListOldCotizacion : cotizacionListOld) {
                if (!cotizacionListNew.contains(cotizacionListOldCotizacion)) {
                    cotizacionListOldCotizacion.setIdPedido(null);
                    cotizacionListOldCotizacion = em.merge(cotizacionListOldCotizacion);
                }
            }
            for (Cotizacion cotizacionListNewCotizacion : cotizacionListNew) {
                if (!cotizacionListOld.contains(cotizacionListNewCotizacion)) {
                    Pedido oldIdPedidoOfCotizacionListNewCotizacion = cotizacionListNewCotizacion.getIdPedido();
                    cotizacionListNewCotizacion.setIdPedido(pedido);
                    cotizacionListNewCotizacion = em.merge(cotizacionListNewCotizacion);
                    if (oldIdPedidoOfCotizacionListNewCotizacion != null && !oldIdPedidoOfCotizacionListNewCotizacion.equals(pedido)) {
                        oldIdPedidoOfCotizacionListNewCotizacion.getCotizacionList().remove(cotizacionListNewCotizacion);
                        oldIdPedidoOfCotizacionListNewCotizacion = em.merge(oldIdPedidoOfCotizacionListNewCotizacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedido.getIdPedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getIdPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            Estadopedido idEstado = pedido.getIdEstado();
            if (idEstado != null) {
                idEstado.getPedidoList().remove(pedido);
                idEstado = em.merge(idEstado);
            }
            Cliente idCliente = pedido.getIdCliente();
            if (idCliente != null) {
                idCliente.getPedidoList().remove(pedido);
                idCliente = em.merge(idCliente);
            }
            List<PedidoDetalle> pedidoDetalleList = pedido.getPedidoDetalleList();
            for (PedidoDetalle pedidoDetalleListPedidoDetalle : pedidoDetalleList) {
                pedidoDetalleListPedidoDetalle.setIdPedido(null);
                pedidoDetalleListPedidoDetalle = em.merge(pedidoDetalleListPedidoDetalle);
            }
            List<Ordencompra> ordencompraList = pedido.getOrdencompraList();
            for (Ordencompra ordencompraListOrdencompra : ordencompraList) {
                ordencompraListOrdencompra.setIdPedido(null);
                ordencompraListOrdencompra = em.merge(ordencompraListOrdencompra);
            }
            List<GuiaremisionDetalle> guiaremisionDetalleList = pedido.getGuiaremisionDetalleList();
            for (GuiaremisionDetalle guiaremisionDetalleListGuiaremisionDetalle : guiaremisionDetalleList) {
                guiaremisionDetalleListGuiaremisionDetalle.setIdPedido(null);
                guiaremisionDetalleListGuiaremisionDetalle = em.merge(guiaremisionDetalleListGuiaremisionDetalle);
            }
            List<Pagos> pagosList = pedido.getPagosList();
            for (Pagos pagosListPagos : pagosList) {
                pagosListPagos.setIdPedido(null);
                pagosListPagos = em.merge(pagosListPagos);
            }
            List<Cotizacion> cotizacionList = pedido.getCotizacionList();
            for (Cotizacion cotizacionListCotizacion : cotizacionList) {
                cotizacionListCotizacion.setIdPedido(null);
                cotizacionListCotizacion = em.merge(cotizacionListCotizacion);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public List<Pagos> listadoxidPedido(int idPedido) {
        EntityManager em = getEntityManager();
        List<Pagos> lista = new ArrayList();
        try {
            Query q = em.createQuery("SELECT p FROM Pagos p WHERE p.idPedido.idPedido = :idPedido").setParameter("idPedido", idPedido);
            lista = (List<Pagos>) q.getResultList();
            System.out.println("Listado por pedido" + lista.size());

        } finally {
            em.close();
        }
        return lista;
    }

    public List<PedidoDetalle> listadoxpedidodetalle(int idPedido) {
        EntityManager em = getEntityManager();
        List<PedidoDetalle> lista = new ArrayList();
        try {
            Query q = em.createQuery("SELECT p FROM PedidoDetalle p WHERE p.idPedido.idPedido = :idPedido").setParameter("idPedido", idPedido);
            lista = (List<PedidoDetalle>) q.getResultList();
            System.out.println("Listado por PedidoDetalle" + lista.size());

            //}catch(Exception e){
            //  System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return lista;
    }

    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
