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
import model.controllers.EstadopagoJpaController;
import model.controllers.PagosJpaController;
import model.controllers.PedidoJpaController;
import model.entities.Estadopago;
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
    private EstadopagoJpaController repo2;
   
    
    public PagoController() {
        em = getEntityManager();
        repo = new PagosJpaController(emf);
        repo1 = new PedidoJpaController(emf);
        repo2 = new EstadopagoJpaController(emf);
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
        
        List<Estadopago> estaPago = repo2.findEstadopagoEntities();
       
        mv.addObject("estadopago", estaPago);
        mv.addObject("pago", new Pagos());
        
        mv.setViewName("RegistrarPago");
        return mv;
    }
    
    @RequestMapping(value = "RegistrarPago.htm",method = RequestMethod.POST)
    public ModelAndView NuevoPago(@ModelAttribute("pago") Pagos p, HttpServletRequest request) throws Exception{
          
        //String num = request.getParameter("numeroOperacion");
        
        int id = Integer.parseInt(request.getParameter("idPedido"));
        /*double mon = Double.parseDouble(request.getParameter("monto"));
        String fecha = request.getParameter("fecha");
        String banco = request.getParameter("banco");
        int estado = Integer.parseInt(request.getParameter("idEstadoPago"));
        
        Pagos p = new Pagos();
        
        p.setNumeroOperacion(num);
        p.setIdPedido(repo1.findPedido(id));
        p.setFecha(fecha);
        p.setMonto(mon);
        p.setBanco(banco);
        p.setIdEstadoPago(repo2.findEstadopago(estado));*/
        
        p.setIdPedido(repo1.findPedido(id));
        repo.create(p);
        
        return new ModelAndView("redirect:/listapedidos.htm");
    }
    
    
}
