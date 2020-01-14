package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.controllers.ClienteJpaController;
import model.controllers.CotizacionDetalleJpaController;
import model.controllers.CotizacionJpaController;
import model.controllers.PedidoJpaController;
import model.controllers.PedidoDetalleJpaController;
import model.entities.Cliente;
import model.entities.Cotizacion;
import model.entities.CotizacionDetalle;
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

    public CotizacionController() {
        em = getEntityManager();
        repo = new ClienteJpaController(emf);
        repo2 = new PedidoJpaController(emf);
        repo3 = new CotizacionJpaController(emf);
        repo4 = new CotizacionDetalleJpaController(emf);
        repo5 = new PedidoDetalleJpaController(emf);

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

        mv.addObject("detalle", pdtem);

        mv.addObject("clientes", clty);

        mv.addObject("cotizacion", new Cotizacion());

        mv.setViewName("Cotizacion");

        return mv;
    }

    @RequestMapping(value = "generarcotizacion.htm", method = RequestMethod.POST)

    public ModelAndView GenerarCotizacion(HttpServletRequest request) throws Exception {

        int id = Integer.parseInt(request.getParameter("idPedido"));

        List<Pedido> p = repo2.findPedidoEntities();

        List<PedidoDetalle> pd = repo5.findPedidoDetalleEntities();
        
        Cotizacion c = new Cotizacion();

        c.setCotizacionDetalleList(new ArrayList<CotizacionDetalle>());

        double sub = Double.parseDouble(request.getParameter("subTotal"));

        String imp = request.getParameter("imp");
        
        double impo = Double.parseDouble(imp);

        String igv = request.getParameter("igv");
        
        double igvv = Double.parseDouble(igv);

        String tot = request.getParameter("total");
        
        double total = Double.parseDouble(tot);
        
        String fecha = request.getParameter("fechaEmision");
        
        String observacion = request.getParameter("observacion");

        for (PedidoDetalle pdt : pd) {

            if (pdt.getIdPedido().getIdPedido() == id) {
                List<CotizacionDetalle> lista = new ArrayList<>(repo4.listadoxpedido(pdt.getIdDetallePedido()));

                if (lista.size() == 0) {

                    CotizacionDetalle detalleC = new CotizacionDetalle();

                    detalleC.setIdCotizacion(c);
                    detalleC.setIdDetallePedido(pdt);
                    detalleC.setSubTotal(sub);

                }
            }
        }
        
        for(Pedido PD : p){
            if(PD.getIdPedido() == id){
                //List<Pedido> pedido = new ArrayList<>(repo2.listadoxpedidos(PD.getIdPedido()));
                
                c.setIdPedido(PD);
            }
        }
        

        //c.setIdPedido(pedido);
         
        c.setFechaEmision(fecha);
        
        c.setObservacion(observacion);

        c.setImporte(impo);

        c.setIgv(igvv);

        c.setTotal(total);

        repo3.create(c);

        return new ModelAndView("redirect:/listapedidostrabajador.htm");
    }

}
