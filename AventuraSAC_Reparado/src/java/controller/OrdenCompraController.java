package controller;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.controllers.OrdencompraDetalleJpaController;
import model.controllers.OrdencompraJpaController;
import model.controllers.ProveedorJpaController;
import model.entities.Ordencompra;
import model.entities.Proveedor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Sabrina Bv
 */
@Controller
public class OrdenCompraController  {


    private EntityManager em;
    private EntityManagerFactory emf;
    private OrdencompraJpaController repo;
    private OrdencompraDetalleJpaController repo1;
    private ProveedorJpaController repo2;

    
    public OrdenCompraController() {
        em = getEntityManager();
        repo = new OrdencompraJpaController(emf);
        repo1 = new OrdencompraDetalleJpaController(emf);
        repo2 = new ProveedorJpaController(emf);
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
        
        List<Proveedor> proveedor = repo2.findProveedorEntities();
        
        mv.addObject("proveedor" , proveedor);
        
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
