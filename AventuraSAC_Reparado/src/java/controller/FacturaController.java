package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.controllers.PedidoJpaController;
import model.entities.Factura;
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
    
    private EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }
    
    @RequestMapping(value = "Factura.htm",method = RequestMethod.GET)
    
    public ModelAndView NuevoPago(Model model) {
        
        ModelAndView mv = new ModelAndView();
        
        mv.addObject("factura", new Factura());
        
        mv.setViewName("Factura");
        return mv;
    }

}
