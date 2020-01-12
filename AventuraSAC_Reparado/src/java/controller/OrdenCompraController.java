package controller;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.controllers.OrdencompraDetalleJpaController;
import model.controllers.OrdencompraJpaController;
import model.entities.Ordencompra;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Sabrina Bv
 */
public class OrdenCompraController  {


    private EntityManager em;
    private EntityManagerFactory emf;
    private OrdencompraJpaController repo;
    private OrdencompraDetalleJpaController repo1;

    
    public OrdenCompraController() {
        em = getEntityManager();
        repo = new OrdencompraJpaController(emf);
    }

    private EntityManager getEntityManager() {
        
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }
    @RequestMapping(value = "OrdenCompra.htm", method = RequestMethod.GET)
    
    public ModelAndView NuevaOrdenCompra(Model model) {
        
        ModelAndView mv = new ModelAndView();

        model.addAttribute("ordencompra", new Ordencompra());/*Guiaremision es la clase osea la entidad*/
        
        mv.setViewName("OrdenCompra");
        
        return mv;
    }
    
    @RequestMapping(value = "OrdenCompra.htm", method = RequestMethod.POST)
    
    public ModelAndView NuevaFichaTecnica(@ModelAttribute("ordencompra") Ordencompra o) throws Exception{
        
        repo.create(o);
        
        return new ModelAndView("redirect:/menu.htm");
    }
    
}
