package controller;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.controllers.GuiaremisionJpaController;
import model.controllers.PedidoJpaController;
import model.entities.Guiaremision;
import model.entities.GuiaremisionDetalle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GuiaRemisionController  {


    private EntityManager em;
    private EntityManagerFactory emf;
    private PedidoJpaController repo;
    private GuiaremisionJpaController repo1;
    
  
    
    public GuiaRemisionController() {
        em = getEntityManager();
        repo1 = new GuiaremisionJpaController(emf);
        
    }

    private EntityManager getEntityManager() {
        
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }
    
    @RequestMapping(value = "GuiaRemision.htm", method = RequestMethod.GET)
    
    public ModelAndView NuevaGuiaRemision(Model model) {
        
        ModelAndView mv = new ModelAndView();

        model.addAttribute("guiaremision", new Guiaremision());
        
        mv.setViewName("GuiaRemision");
        
        return mv;
    }
    @RequestMapping(value = "GuiaRemisionDetalle.htm", method = RequestMethod.GET)    
    public ModelAndView NuevaGuiaRemisionDetalle(Model model) {
        
        ModelAndView mv = new ModelAndView();

        model.addAttribute("guiaremisiondetalle", new GuiaremisionDetalle());
        
        mv.setViewName("GuiaRemision");
        
        return mv;
    }
    
    @RequestMapping(value = "GuiaRemision.htm", method = RequestMethod.POST)
    
    public ModelAndView NuevaGuiaRemision(@ModelAttribute("guiaremision") Guiaremision g) throws Exception{
        
        repo1.create(g);
        
        return new ModelAndView("redirect:/menu.htm");
    }
    
    
}   

  