package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.controllers.ClienteJpaController;
import model.controllers.CotizacionDetalleJpaController;
import model.controllers.CotizacionJpaController;
import model.controllers.EstadoJpaController;
import model.controllers.FichatecnicaJpaController;
import model.controllers.PedidoJpaController;
import model.controllers.PedidoDetalleJpaController;
import model.entities.Cliente;
import model.entities.Cotizacion;
import model.entities.CotizacionDetalle;
import model.entities.Estado;
import model.entities.Fichatecnica;
import model.entities.Pedido;
import model.entities.PedidoDetalle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CotizacionController {

    private EntityManager em;
    private EntityManagerFactory emf;
    private ClienteJpaController repo;
    private PedidoJpaController repo2;
    private CotizacionJpaController repo3;
    private CotizacionDetalleJpaController repo4;
    private PedidoDetalleJpaController repo5;
    private EstadoJpaController repo6;
    private FichatecnicaJpaController repo7;

    public CotizacionController() {
        em = getEntityManager();
        repo = new ClienteJpaController(emf);
        repo2 = new PedidoJpaController(emf);
        repo3 = new CotizacionJpaController(emf);
        repo4 = new CotizacionDetalleJpaController(emf);
        repo5 = new PedidoDetalleJpaController(emf);
        repo6 = new EstadoJpaController(emf);
        repo7 = new FichatecnicaJpaController(emf);
    }

    private EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }

    @RequestMapping(value = "Cotizacion.htm", method = RequestMethod.GET)

    public ModelAndView NuevoCotizacion(Model model, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        List<PedidoDetalle> pd = repo5.findPedidoDetalleEntities();

        List<PedidoDetalle> pdtem = new ArrayList();

        List<Estado> estado = repo6.findEstadoEntities();

        int id = Integer.parseInt(request.getParameter("idPedido"));

        request.setAttribute("idPedido", id);

        List<Pedido> p = repo2.findPedidoEntities();

        List<Cliente> cl = repo.findClienteEntities();

        List<Cliente> clty = new ArrayList();

        boolean en = false;

        for (PedidoDetalle d : pd) {

            if (d.getIdPedido().getIdPedido() == id) {

                List<CotizacionDetalle> detalle = repo4.listadoxpedido(d.getIdDetallePedido());

                if (detalle.size() == 0) {
                    pdtem.add(d);
                }

            }

        }

        for (PedidoDetalle d : pd) {

            if (d.getIdPedido().getIdPedido() == id) {

                for (Cliente c : cl) {

                    if (d.getIdPedido().getIdCliente().getIdCliente() == c.getIdCliente()) {
                        en = true;
                        clty.add(c);

                    }

                }
                break;
            }
        }

        mv.addObject("estado", estado);

        mv.addObject("detalle", pdtem);

        mv.addObject("clientes", clty);

        mv.addObject("cotizacion", new Cotizacion());

        mv.setViewName("Cotizacion");

        return mv;
    }

    
    @RequestMapping(value = "generarcotizacion.htm", method = RequestMethod.POST)

    public ModelAndView GenerarCotizacion(HttpServletRequest request) throws Exception {

        int id = Integer.parseInt(request.getParameter("idPedido"));
        String fecha = request.getParameter("fechaEmision");
        String fichas = request.getParameter("fichas");
        double impo = Double.parseDouble(request.getParameter("imp"));
        double igvv = Double.parseDouble(request.getParameter("igv"));
        double total = Double.parseDouble(request.getParameter("total"));
        String observacion = request.getParameter("observacion");

//        List<Pedido> p = repo2.findPedidoEntities();
//
//        List<PedidoDetalle> pd = repo5.findPedidoDetalleEntities();
//
//        List<PedidoDetalle> pdtem = new ArrayList();
        Cotizacion c = new Cotizacion();
        c.setCotizacionDetalleList(new ArrayList<CotizacionDetalle>());

        c.setIdPedido(repo2.findPedido(id));
        c.setFechaEmision(fecha);
        c.setImporte(impo);
        c.setIgv(igvv);
        c.setTotal(total);
        c.setObservacion(observacion);

        StringTokenizer stD = new StringTokenizer(fichas, ";");
        int detalles = stD.countTokens();
        int idDetallePedido = 0;
        int idEstado = 0;
        double subTotal = 0;
        for (int i = 0; i < detalles; i++) {
            // Obtenemos los datos de cada pedido
            String linea = stD.nextToken();
            StringTokenizer stDatos = new StringTokenizer(linea, ",");
            idDetallePedido = Integer.parseInt(stDatos.nextToken());
            idEstado = Integer.parseInt(stDatos.nextToken());
            subTotal = Double.parseDouble(stDatos.nextToken());

            PedidoDetalle pedidoDet = repo5.findPedidoDetalle(idDetallePedido);

            CotizacionDetalle detalleC = new CotizacionDetalle();
            detalleC.setIdDetalleCotizacion(0);
            detalleC.setIdCotizacion(c);
            detalleC.setIdDetallePedido(pedidoDet);
            detalleC.setSubTotal(subTotal);
            c.getCotizacionDetalleList().add(detalleC);

            // Actualizar el estado de la ficha tecnica
            Fichatecnica ft = pedidoDet.getIdFicha();
            ft.setIdEstado(repo6.findEstado(idEstado));
            repo7.edit(ft);
        }
        
        repo3.create(c);


        return new ModelAndView("redirect:/listapedidostrabajador.htm");
    }
    
    
    @RequestMapping(value = "vercotizacion.htm", method = RequestMethod.GET)

    public ModelAndView VerCotizacion(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        
        int id = Integer.parseInt(request.getParameter("cotizacionList.get(0)"));
        
        Cotizacion obj = repo3.findCotizacion(id);
        
        List<CotizacionDetalle> detalle = repo4.findCotizacionDetalleEntities();
        List<CotizacionDetalle> repo1 = new ArrayList();
        List<Cliente> cliente = repo.findClienteEntities();
        List<Cliente> repo = new ArrayList();
        
        for(Cliente c : cliente){
            if(c.getIdCliente() == obj.getIdPedido().getIdCliente().getIdCliente()){
                repo.add(c);
            }
        }
        
        for(CotizacionDetalle co : detalle){
            if(co.getIdCotizacion().getIdCotizacion() == obj.getIdCotizacion()){
                //List<CotizacionDetalle> detalles = repo4.listadoxpedido(id);
                //if(detalles.size() == 0){
                repo1.add(co);
                //}
            }
        }
        
        
        mv.addObject("detalle", repo1);
        mv.addObject("cliente", repo);
        mv.addObject("cotizacion", obj);
        mv.setViewName("CotizacionVista");

        return mv;
    }


}
