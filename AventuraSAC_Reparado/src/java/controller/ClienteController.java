
package controller;

import com.google.gson.Gson;
import model.controllers.ClienteJpaController;
import model.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.controllers.DistritoJpaController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Distrito;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClienteController {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    private ClienteJpaController repo;
    private DistritoJpaController repo1;
    
    public ClienteController() {
        em = getEntityManager();
        repo = new ClienteJpaController(emf);
        repo1 = new DistritoJpaController(emf);
    }

    private EntityManager getEntityManager() {
        
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }
    
    @RequestMapping("clientes.htm")
    
    public ModelAndView Listar(HttpServletRequest request) {
        
        List<Cliente> clientes = new ArrayList<>();
        
        clientes = repo.findClienteEntities();

        ModelAndView mv = new ModelAndView();
        
        mv.addObject("clientes", clientes);
        
        mv.setViewName("clientes");
        
        return mv;
    }
    
    @RequestMapping(value = "nuevocliente.htm", method = RequestMethod.GET)
    
    public ModelAndView NuevoCliente(Model model) {
        
        ModelAndView mv = new ModelAndView();
        
        List<Distrito> distritos = repo1.findDistritoEntities();

        mv.addObject("listaDistrito", distritos);
        
        model.addAttribute("cliente", new Cliente());
        
        mv.setViewName("nuevocliente");
        
        return mv;
    }
    
    @RequestMapping(value = "nuevocliente.htm", method = RequestMethod.POST)
    
    public ModelAndView NuevoCliente(@ModelAttribute("cliente") Cliente c) throws Exception{
        
        repo.create(c);
        
        return new ModelAndView("redirect:/clientes.htm");
    }
    
    
    @RequestMapping(value = "editarcliente.htm", method = RequestMethod.GET)
    
    public ModelAndView EditarCliente(HttpServletRequest request) {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Cliente obj = repo.findCliente(id);
        
        ModelAndView mv = new ModelAndView();
        
        List<Distrito> distritos = repo1.findDistritoEntities();
        
        mv.addObject("listaDistrito", distritos);
        
        mv.addObject("cliente", obj);
       
        mv.setViewName("editarcliente");
        
        return mv;
        
    }
    
    @RequestMapping(value = "editarcliente.htm", method = RequestMethod.POST)
    
    public ModelAndView EditarCliente(@ModelAttribute("cliente") Cliente c) throws Exception {
        
        repo.edit(c);

        return new ModelAndView("redirect:/clientes.htm");
    }
    
     @RequestMapping(value = "eliminarcliente.htm")
     
    public ModelAndView EliminarCliente(HttpServletRequest request) throws NonexistentEntityException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        repo.destroy(id);

        return new ModelAndView("redirect:/clientes.htm");
    }
    
    
    @RequestMapping(value = "obtienecliente.htm", method = RequestMethod.GET)
    public @ResponseBody
    String obtenerCliente(@RequestParam(value="id") String id) {
        // Obtenemos el cliente
        Cliente c = repo.findCliente(Integer.parseInt(id));
        // Asignamos nulo a los datos que se refieren a otras tablas
        c.setPedidoList(null);
        c.setIdDistrito(null);
        // Obtenemos la información en formato JSON usando la librería Json
        return new Gson().toJson(c);
    }
    
    @RequestMapping(value = "obtienecliente.htm", method = RequestMethod.POST)
    public ModelAndView obtenerCliente(@ModelAttribute("cliente") Cliente c) throws Exception {
        
        repo.edit(c);

        return new ModelAndView("redirect:/clientes.htm");
    }


}
