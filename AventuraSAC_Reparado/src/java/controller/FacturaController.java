package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.controllers.ClienteJpaController;
import model.controllers.DetallefacturaJpaController;
import model.controllers.FacturaJpaController;
import model.controllers.PedidoDetalleJpaController;
import model.controllers.PedidoJpaController;
import model.entities.Cliente;
import model.entities.Detallefactura;
import model.entities.Factura;
import model.entities.Pedido;
import model.entities.PedidoDetalle;
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
    private PedidoDetalleJpaController repo2;
    private ClienteJpaController repo3;
    private FacturaJpaController repo4;
    private DetallefacturaJpaController repo5;
    
    public FacturaController() {
        em = getEntityManager();
        repo1 = new PedidoJpaController(emf);
        repo2 = new PedidoDetalleJpaController(emf);
        repo3 = new ClienteJpaController(emf);
        repo4 = new FacturaJpaController(emf);
        repo5 = new DetallefacturaJpaController(emf);
    }
    
    private EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }
    
    @RequestMapping(value = "Factura.htm",method = RequestMethod.GET)
    
    public ModelAndView NuevoPago(Model model, HttpServletRequest request) {
        
        ModelAndView mv = new ModelAndView();
        
        int id = Integer.parseInt(request.getParameter("idPedido"));
        request.setAttribute("idPedido", id);
        
        String fechaEmision = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        request.setAttribute("fecha", fechaEmision);
        
        List<Pedido> p = repo1.findPedidoEntities();
        List<PedidoDetalle> det = repo2.findPedidoDetalleEntities();
        List<PedidoDetalle> dettemp = new ArrayList();
        List<Cliente> c = repo3.findClienteEntities();
        List<Cliente> ctemp = new ArrayList();
        
        boolean en = false;
        
        for(Pedido pe : p){
            if(pe.getIdPedido() == id){
                for(Cliente cli : c){
                    if(pe.getIdCliente().getIdCliente() == cli.getIdCliente()){
                        en = true;
                        ctemp.add(cli);
                    }
                }
            }
        }
        
        for(PedidoDetalle pd : det){
            if(pd.getIdPedido().getIdPedido() == id){
                List<Detallefactura> fd = repo5.listadoxpedido(pd.getIdDetallePedido());
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

}
