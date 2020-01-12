package controller;

import com.google.gson.Gson;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.controllers.CargoJpaController;
import model.controllers.ClienteJpaController;
import model.controllers.DistritoJpaController;
import model.controllers.EmpleadoJpaController;
import model.controllers.PedidoJpaController;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Cargo;
import model.entities.Cliente;
import model.entities.Distrito;
import model.entities.Empleado;
import model.entities.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private EntityManager em;
    private EntityManagerFactory emf;
    private ClienteJpaController repo;
    private EmpleadoJpaController repo1;
    private DistritoJpaController repo2;
    private CargoJpaController repo3;
    private PedidoJpaController repo4;

    public LoginController() {
        em = getEntityManager();
        repo = new ClienteJpaController(emf);
        repo1 = new EmpleadoJpaController(emf);
        repo2 = new DistritoJpaController(emf);
        repo3 = new CargoJpaController(emf);
        repo4 = new PedidoJpaController(emf);
    }

    private EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }

    @RequestMapping("login.htm")

    public ModelAndView Login() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("login");

        return mv;
    }

    @RequestMapping("menu.htm")

    public ModelAndView Menu() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("MenuTrabajador");

        return mv;
    }

    @RequestMapping("menucliente.htm")

    public ModelAndView MenuCliente() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("MenuCliente");

        return mv;
    }

    @RequestMapping("menualmacen.htm")

    public ModelAndView MenuAlmacen() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("MenuAlmacen");

        return mv;
    }

    @RequestMapping("menuventas.htm")

    public ModelAndView MenuVentas() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("MenuVentas");

        return mv;
    }

    @RequestMapping("menulogistica.htm")

    public ModelAndView MenuLogistica() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("MenuLogistica");

        return mv;
    }

    @RequestMapping(value = "validar.htm", method = RequestMethod.POST)

    public ModelAndView getValidaLogin(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();

        String usuario = request.getParameter("txtusuario");
        String clave = request.getParameter("txtclave");
        //int cargo = Integer.parseInt(request.getParameter("txtcargo"));

        List<Empleado> lista = repo1.findEmpleadoEntities();
        List<Cliente> lista1 = repo.findClienteEntities();
        List<Cargo> lista3 = repo3.findCargoEntities();

        boolean encontrado = false;

        for (Empleado e : lista) {

            if (e.getUsuario().equals(usuario) && e.getClave().equals(clave)) {

                encontrado = true;
                request.getSession().setAttribute("usuario", e);
                mv.addObject("usuario", e);
                System.out.println("US: " + e.getIdCargo().getDetalle());

                //for (Cargo x : lista3) {
                    
                    if (e.getIdCargo().getIdCargo().equals(4)) {
                        //mv.addObject("cargo", x);
                        mv.setViewName("MenuAlmacen");
                         break;
                    }
                    
                     if (e.getIdCargo().getIdCargo().equals(3)) {
                        //mv.addObject("cargo", x);
                        mv.setViewName("MenuTrabajador");
                         break;
                    }
                    
                    if (e.getIdCargo().getIdCargo().equals(1)) {
                        //mv.addObject("cargo", x);
                        mv.setViewName("MenuVentas");
                         break;

                    }
                    
                    if (e.getIdCargo().getIdCargo().equals(2)) {
                        //mv.addObject("cargo", x);
                        mv.setViewName("MenuLogistica");
                         break;
                    }
                    
                //}

            }

        }

        if (encontrado == false) {
            for (Cliente c : lista1) {
                if (c.getUsuario().equals(usuario) && c.getClave().equals(clave)) {
                    encontrado = true;
                    request.getSession().setAttribute("usuario", c);
                    mv.addObject("usuario", c);
                    mv.setViewName("MenuCliente");
                    break;
                }
            }
        }

        if (encontrado == false) {
            request.setAttribute("mensaje", "No se encuentran los datos");
            mv.setViewName("login");
        } 
        return mv;

    }

    /*@RequestMapping(value = "nuevo.htm", method = RequestMethod.GET)
    public @ResponseBody
    String obtenerCliente() {
        
        List<Distrito> distritos = repo2.findDistritoEntities();


        return new Gson().toJson(c);
    }*/
    @RequestMapping(value = "nuevo.htm", method = RequestMethod.GET)

    public ModelAndView NuevoCliente(Model model) {

        ModelAndView mv = new ModelAndView();

        List<Distrito> distritos = repo2.findDistritoEntities();

        mv.addObject("listaDistrito", distritos);

        model.addAttribute("cliente", new Cliente());

        mv.setViewName("Registrarusuariocli");

        return mv;
    }

    @RequestMapping(value = "nuevo.htm", method = RequestMethod.POST)

    public ModelAndView NuevoCliente(@ModelAttribute("cliente") Cliente c) throws Exception {

        repo.create(c);

        return new ModelAndView("redirect:/login.htm");
    }
}
