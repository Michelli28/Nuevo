/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.entities.Pagos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.controllers.PagosJpaController;
import model.controllers.PedidoJpaController;
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
   
    
    public PagoController() {
        em = getEntityManager();
        repo = new PagosJpaController(emf);
        repo1 = new PedidoJpaController(emf);
    }

    private EntityManager getEntityManager() {
        
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }
    
    @RequestMapping(value = "RegistrarPago.htm",method = RequestMethod.GET)
    
    public ModelAndView NuevoPago(Model model) {
        
        ModelAndView mv = new ModelAndView();
        
        mv.addObject("pago", new Pagos());
        
        mv.setViewName("RegistrarPago");
        return mv;
    }
    
    @RequestMapping(value = "RegistrarPago.htm",method = RequestMethod.POST)
    public ModelAndView NuevoProveedor(@ModelAttribute("proveedor") Pagos c) throws Exception{
        
        repo.create(c);
        
        return new ModelAndView("redirect:/listapedidos.htm");
    }
    
    
}
