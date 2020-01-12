package controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.controllers.ClienteJpaController;
import model.controllers.CotizacionJpaController;
import model.controllers.EstadoJpaController;
import model.controllers.FichatecnicaJpaController;
import model.controllers.PedidoDetalleJpaController;
import model.controllers.PedidoJpaController;
import model.entities.Cliente;
import model.entities.Cotizacion;
import model.entities.Estado;
import model.entities.Fichatecnica;
import model.entities.Pedido;
import model.entities.PedidoDetalle;
import org.eclipse.persistence.sessions.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PedidoController {

    private EntityManager em;
    private EntityManagerFactory emf;
    private PedidoJpaController repo;
    private ClienteJpaController repo1;
    private FichatecnicaJpaController repo2;
    private PedidoDetalleJpaController repo3;
    private EstadoJpaController repo4;
    private CotizacionJpaController repo5;

    public PedidoController() {
        em = getEntityManager();
        repo = new PedidoJpaController(emf);
        repo1 = new ClienteJpaController(emf);
        repo2 = new FichatecnicaJpaController(emf);
        repo3 = new PedidoDetalleJpaController(emf);
        repo4 = new EstadoJpaController(emf);
        repo5 = new CotizacionJpaController(emf);
    }

    private EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }

        return emf.createEntityManager();
    }

    @RequestMapping("listapedidos.htm")

    public ModelAndView ListaPedidos(HttpServletRequest request) {
        
        Cliente c = (Cliente) request.getSession().getAttribute("usuario");

        List<Pedido> pedidos = repo.findPedidoEntities();
        
        List<Cotizacion> cotiz = repo5.findCotizacionEntities();
        
        List<Pedido> pedidostemporal = new ArrayList();
        
        ModelAndView mv = new ModelAndView();
        
        for(Pedido x: pedidos){
            
            if(x.getIdCliente().getIdCliente() == c.getIdCliente()){
                
                pedidostemporal.add(x);
                
            }
        }
        
        /*for(Pedido p : pedidos){
            for(Cotizacion co : cotiz){
                if(p.getIdPedido() == co.getIdPedido().getIdPedido()){
                    conuevo.add(co);
                }
            }
        }*/
        
        mv.addObject("cotizar", cotiz);

        mv.addObject("pedidos", pedidostemporal);
        
        mv.setViewName("listapedidos");

        return mv;
    }
    
        
    @RequestMapping("listapedidostrabajador.htm")

    public ModelAndView ListaPedidosTrabajador(HttpServletRequest request) {

        List<Pedido> pedidos = new ArrayList();

        pedidos = repo.findPedidoEntities();

        ModelAndView mv = new ModelAndView();
        
        request.getSession().setAttribute("pedidos", pedidos);

        mv.addObject("pedidos", pedidos);

        mv.setViewName("listapedidostrabajador");

        return mv;
    }
    
    @RequestMapping(value = "nuevopedido.htm", method = RequestMethod.GET)

    public ModelAndView NuevoPedido(Model model, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        Cliente c = (Cliente) request.getSession().getAttribute("usuario");
        //repo2 = new FichatecnicaJpaController(emf);
        List<Fichatecnica> ficha = repo2.findFichatecnicaEntities();
        
        List<Fichatecnica> fichatemporal = new ArrayList();
        
        List<Estado> estado = repo4.findEstadoEntities();

        for(Fichatecnica x : ficha) {
            
            if(x.getIdCliente().getIdCliente() == c.getIdCliente()) {
                
                List<PedidoDetalle> lista = repo3.listadoxficha(x.getIdFicha());
                
                if(lista.size() == 0){

                fichatemporal.add(x); 
                
                }
            }    
        }
        mv.addObject("estado", estado);
        
        mv.addObject("ficha", fichatemporal);
        
        model.addAttribute("pedido", new Pedido());

        mv.setViewName("nuevopedido");

        return mv;
    }

    @RequestMapping(value = "nuevopedido.htm", method = RequestMethod.POST)

    public ModelAndView NuevoPedido(@ModelAttribute("pedido") Pedido p, HttpServletRequest request) throws Exception {

        Cliente c = (Cliente) request.getSession().getAttribute("usuario");
        
        List<Fichatecnica> ficha = new ArrayList<>(repo2.findFichatecnicaEntities());
                
        p.setPedidoDetalleList(new ArrayList<PedidoDetalle>());

        for (Fichatecnica x : ficha){
            
            if(x.getIdCliente().getIdCliente() == c.getIdCliente()) {
                
                
                List<PedidoDetalle> lista = new ArrayList<>(repo3.listadoxficha(x.getIdFicha()));
                
                if(lista.size() == 0){

                PedidoDetalle detalleP = new PedidoDetalle();

                detalleP.setIdPedido(p);
                detalleP.setIdFicha(x);

                p.getPedidoDetalleList().add(detalleP);
                
                }
            }    
          
    }
    
    p.setIdCliente (c);

    repo.create (p);

    return new ModelAndView("redirect:/listapedidos.htm");
    }

    
}
