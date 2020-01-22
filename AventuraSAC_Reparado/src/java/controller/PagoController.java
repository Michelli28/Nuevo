 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.entities.Pagos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.controllers.BancoJpaController;
import model.controllers.EstadopedidoJpaController;
import model.controllers.PagosJpaController;
import model.controllers.PedidoJpaController;
import model.entities.Banco;
import model.entities.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagoController {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    private PagosJpaController repo;
    private PedidoJpaController repo1;
    private EstadopedidoJpaController repo2;
    private BancoJpaController repo3;
    
    public PagoController() {
        em = getEntityManager();
        repo = new PagosJpaController(emf);
        repo1 = new PedidoJpaController(emf);
        repo2 = new EstadopedidoJpaController(emf);
        repo3 = new BancoJpaController(emf);
    }

    private EntityManager getEntityManager() {
        
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }
    
    @RequestMapping(value = "RegistrarPago.htm",method = RequestMethod.GET)
    
    public ModelAndView NuevoPago(Model model, HttpServletRequest request) {
        
        ModelAndView mv = new ModelAndView();
        
        int id = Integer.parseInt(request.getParameter("idPedido"));
        request.setAttribute("idPedido", id);
        
        List<Banco> banco = repo3.findBancoEntities();
        
        mv.addObject("banco", banco);
        mv.addObject("pago", new Pagos());
        mv.setViewName("RegistrarPago");
        return mv;
    }
    
    @RequestMapping(value = "Registrar.htm",method = RequestMethod.POST)
    
    public ModelAndView NuevoPago(HttpServletRequest request) throws Exception{
          
        String num = request.getParameter("numeroOperacion");
        int id = Integer.parseInt(request.getParameter("idPedido"));
        String fecha = request.getParameter("fecha");
        double mon = Double.parseDouble(request.getParameter("monto")); 
        int banco = Integer.parseInt(request.getParameter("idBanco"));
        int idEstado = Integer.parseInt(request.getParameter("idEstado"));

        Pagos p = new Pagos();
        
        //double sal = pedido.getSaldo() - p.acumular(mon);
        
        p.setNumeroOperacion(num);
        p.setIdPedido(repo1.findPedido(id));
        p.setFecha(fecha);
        p.setMonto(mon);
        p.setIdBanco(repo3.findBanco(banco));
        
         
        repo.create(p);
        
        Pedido pedido = repo1.findPedido(id);
        List<Pagos> pagos = repo1.listadoxidPedido(id);
        double adelanto = 0;
        
            for(Pagos pa : pagos){

                adelanto =  adelanto + pa.getMonto();
                pedido.setAcumulado(adelanto);
                repo1.edit(pedido);
            }
            int Estado = 4;
            if(pedido.getAcumulado() >= (pedido.getSaldo()/2)){
                pedido.setIdEstado(repo2.findEstadopedido(Estado));
                repo1.edit(pedido);
            }

        
        return new ModelAndView("redirect:/listapedidos.htm");
    }
    
    @RequestMapping(value = "RegistrarPago1.htm",method = RequestMethod.GET)
    
    public ModelAndView NuevoPago1(Model model, HttpServletRequest request) {
        
        ModelAndView mv = new ModelAndView();
        
        int id = Integer.parseInt(request.getParameter("idPedido"));
        request.setAttribute("idPedido", id);
        
        Pedido p = repo1.findPedido(id);
        mv.addObject("p", p);
        
        List<Banco> banco = repo3.findBancoEntities();
        
        mv.addObject("banco", banco);
        mv.addObject("pago", new Pagos());
        mv.setViewName("RegistrarPago1");
        return mv;
    }
    
    @RequestMapping(value = "Registrar1.htm",method = RequestMethod.POST)
    
    public ModelAndView NuevoPago1(HttpServletRequest request) throws Exception{
          
        String num = request.getParameter("numeroOperacion");
        int id = Integer.parseInt(request.getParameter("idPedido"));
        String fecha = request.getParameter("fecha");
        double mon = Double.parseDouble(request.getParameter("monto")); 
        int banco = Integer.parseInt(request.getParameter("idBanco"));
        int idEstado = Integer.parseInt(request.getParameter("idEstado"));

        Pagos p = new Pagos();
        
        //double sal = pedido.getSaldo() - p.acumular(mon);
        
        p.setNumeroOperacion(num);
        p.setIdPedido(repo1.findPedido(id));
        p.setFecha(fecha);
        p.setMonto(mon);
        p.setIdBanco(repo3.findBanco(banco));
        
         
        repo.create(p);
        
        Pedido pedido = repo1.findPedido(id);
        List<Pagos> pagos = repo1.listadoxidPedido(id);
        double adelanto = 0;
        
            for(Pagos pa : pagos){

                adelanto =  adelanto + pa.getMonto();
                pedido.setAcumulado(adelanto);
                repo1.edit(pedido);
            }
            int Estado = 5;
            /*if(pedido.getAcumulado() >= (pedido.getSaldo()/2)){
                pedido.setIdEstado(repo2.findEstadopedido(Estado));
                repo1.edit(pedido);
            }
            */
            if(pedido.getAcumulado() > (pedido.getSaldo()/2) && pedido.getAcumulado() > (pedido.getSaldo()/2)){
                pedido.setIdEstado(repo2.findEstadopedido(Estado));
                repo1.edit(pedido);
            }
            
            if(pedido.getAcumulado() == (pedido.getSaldo())){
                pedido.setIdEstado(repo2.findEstadopedido(6));
                repo1.edit(pedido);
            }

        
        return new ModelAndView("redirect:/listapedidos.htm");
    }
    
}
