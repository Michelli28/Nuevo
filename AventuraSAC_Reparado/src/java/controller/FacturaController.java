package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.controllers.ClienteJpaController;
import model.controllers.CotizacionDetalleJpaController;
import model.controllers.CotizacionJpaController;
import model.controllers.DetallefacturaJpaController;
import model.controllers.EstadopedidoJpaController;
import model.controllers.FacturaJpaController;
import model.controllers.PedidoDetalleJpaController;
import model.controllers.PedidoJpaController;
import model.entities.Cliente;
import model.entities.Cotizacion;
import model.entities.CotizacionDetalle;
import model.entities.Detallefactura;
import model.entities.Factura;
import model.entities.Pedido;
import model.entities.PedidoDetalle;
import static model.entities.PedidoDetalle_.idDetallePedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FacturaController {

    private EntityManager em;
    private EntityManagerFactory emf;
    private PedidoJpaController repo1;
    private CotizacionJpaController repo2;
    private CotizacionDetalleJpaController repo3;
    private ClienteJpaController repo4;
    private FacturaJpaController repo5;
    private DetallefacturaJpaController repo6;
    private EstadopedidoJpaController repo7;

    public FacturaController() {
        em = getEntityManager();
        repo1 = new PedidoJpaController(emf);
        repo2 = new CotizacionJpaController(emf);
        repo3 = new CotizacionDetalleJpaController(emf);
        repo4 = new ClienteJpaController(emf);
        repo5 = new FacturaJpaController(emf);
        repo6 = new DetallefacturaJpaController(emf);
        repo7 = new EstadopedidoJpaController(emf);
    }

    private EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }

    @RequestMapping(value = "Factura.htm", method = RequestMethod.GET)

    public ModelAndView GenerarFactura(Model model, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        int id = Integer.parseInt(request.getParameter("idPedido"));
        request.setAttribute("idPedido", id);

        String fechaEmision = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        request.setAttribute("fecha", fechaEmision);

        List<Pedido> p = repo1.findPedidoEntities();
        List<Cotizacion> cot = repo2.findCotizacionEntities();
        List<Cotizacion> coti = new ArrayList();
        List<CotizacionDetalle> det = repo3.findCotizacionDetalleEntities();
        List<CotizacionDetalle> dettemp = new ArrayList();
        List<Cliente> c = repo4.findClienteEntities();
        List<Cliente> ctemp = new ArrayList();

        boolean en = false;

        for (Pedido pe : p) {
            if (pe.getIdPedido() == id) {
                for (Cliente cli : c) {
                    if (pe.getIdCliente().getIdCliente() == cli.getIdCliente()) {
                        en = true;
                        ctemp.add(cli);
                    }
                }
            }
        }

        for (Cotizacion co : cot) {
            if (co.getIdPedido().getIdPedido() == id) {

                mv.addObject("coti", co);
            }
        }

        for (CotizacionDetalle pd : det) {
            if (pd.getIdDetallePedido().getIdPedido().getIdPedido() == id) {
                List<Detallefactura> fd = repo6.listadoxpedido(pd.getIdDetalleCotizacion());
                if (fd.size() == 0) {
                    dettemp.add(pd);
                }
            }
        }

        mv.addObject("cliente", ctemp);
        mv.addObject("detalle", dettemp);
        mv.addObject("factura", new Factura());
        mv.setViewName("Factura");
        return mv;
    }

    @RequestMapping(value = "generarfactura.htm", method = RequestMethod.POST)

    public ModelAndView GenerarFactura(HttpServletRequest request) throws Exception {

        int idPedido = Integer.parseInt(request.getParameter("idPedido"));

        String fecha = request.getParameter("fechaEmision");
        double impo = Double.parseDouble(request.getParameter("imp"));
        double igvv = Double.parseDouble(request.getParameter("igv"));
        double total = Double.parseDouble(request.getParameter("total"));
        //int idDetalle = Integer.parseInt(request.getParameter("idDetalleCotizacion"));
        String detalles = request.getParameter("detalles");
        //int id = Integer.parseInt(request.getParameter("idCotizacion"));

        Factura f = new Factura();
        f.setDetallefacturaList(new ArrayList<Detallefactura>());

        f.setIdCotizacion(repo2.findCotizacion(idPedido));
        f.setFecha(fecha);
        f.setImporte(impo);
        f.setIgv(igvv);
        f.setTotal(total);

        StringTokenizer stD = new StringTokenizer(detalles, ";");
        int detalle = stD.countTokens();
        int idCoti = 0;
        double subTotal = 0.0;

        for (int i = 0; i < detalle; i++) {
            // Obtenemos los datos de cada pedido
            String linea = stD.nextToken();
            StringTokenizer stDatos = new StringTokenizer(linea, ",");
            idCoti = Integer.parseInt(stDatos.nextToken());
            subTotal = Double.parseDouble(stDatos.nextToken());

            Detallefactura detalleF = new Detallefactura();

            detalleF.setIdFactura(f);
            detalleF.setIdDetalleFactura(0);
            detalleF.setIdDetalleCotizacion(repo3.findCotizacionDetalle(idCoti));
            detalleF.setSubtotal(subTotal);
            f.getDetallefacturaList().add(detalleF);
        }

        repo5.create(f);

        Pedido pedido = repo1.findPedido(idPedido);
        Cotizacion co = repo2.findCotizacion(idPedido);

        if (co.getIdPedido().getIdPedido() == pedido.getIdPedido()) {
            pedido.setIdEstado(repo7.findEstadopedido(7));
            repo1.edit(pedido);
        }

        return new ModelAndView("redirect:/listapedidostrabajador.htm");
    }

    @RequestMapping(value = "verfactura.htm", method = RequestMethod.GET)

    public ModelAndView VerFactura(Model model, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        int id = Integer.parseInt(request.getParameter("idPedido"));
        request.setAttribute("idPedido", id);

        //List<Pedido> p = repo1.findPedidoEntities();
        List<Factura> fac = repo5.findFacturaEntities();
        List<Detallefactura> detfac = repo6.findDetallefacturaEntities();
        List<Detallefactura> dettem  = new ArrayList();
        List<CotizacionDetalle> det = repo3.findCotizacionDetalleEntities();
        List<CotizacionDetalle> dettemp  = new ArrayList();
        List<Cliente> c = repo4.findClienteEntities();
        List<Cliente> ctemp = new ArrayList();

        boolean en = false;

        Pedido obj = repo1.findPedido(id);

        for (Factura f : fac) {
            if (f.getIdCotizacion().getIdPedido().getIdPedido() == obj.getIdPedido()) {
                for (Cliente cli : c) {
                    if (f.getIdCotizacion().getIdPedido().getIdCliente().getIdCliente() == cli.getIdCliente()) {
                        en = true;
                        ctemp.add(cli);
                    }
                }
            }
        }

        for (Factura f : fac) {
            if (f.getIdCotizacion().getIdPedido().getIdPedido() == obj.getIdPedido()) {
                mv.addObject("factura", f);
            }
        }
        
        for (Detallefactura pd : detfac) {
            if (pd.getIdDetalleCotizacion().getIdCotizacion().getIdPedido().getIdPedido() == obj.getIdPedido()) {
               
                    dettem.add(pd);
                
            }
        }

        mv.addObject("cliente", ctemp);
        mv.addObject("detalle", dettem);
        mv.setViewName("VerFactura");
        return mv;
    }

}
