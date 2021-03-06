package controller;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import model.controllers.ClienteJpaController;
import model.entities.Cliente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.controllers.DistritoJpaController;
import model.controllers.EstadopedidoJpaController;
import model.controllers.MovimientoalmacenJpaController;
import model.controllers.OrdencompraDetalleJpaController;
import model.controllers.OrdencompraJpaController;
import model.controllers.PedidoDetalleJpaController;
import model.controllers.PedidoJpaController;
import model.controllers.TipoitemJpaController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Detallemovimiento;
import model.entities.Distrito;
import model.entities.Movimientoalmacen;
import model.entities.Pedido;
import model.entities.Tipoitem;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovimientoController {

    private EntityManager em;
    private EntityManagerFactory emf;
    private MovimientoalmacenJpaController repo;
    private OrdencompraJpaController repo1;
    private OrdencompraDetalleJpaController repo2;
    private PedidoJpaController repo3;
    private PedidoDetalleJpaController repo4;
    private TipoitemJpaController repo5;
    private EstadopedidoJpaController repo6;

    public MovimientoController() {
        em = getEntityManager();
        repo = new MovimientoalmacenJpaController(emf);
        repo1 = new OrdencompraJpaController(emf);
        repo2 = new OrdencompraDetalleJpaController(emf);
        repo3 = new PedidoJpaController(emf);
        repo4 = new PedidoDetalleJpaController(emf);
        repo5 = new TipoitemJpaController(emf);
        repo6 = new EstadopedidoJpaController(emf);
    }

    private EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }

    @RequestMapping(value = "nuevomovimiento.htm", method = RequestMethod.GET)

    public ModelAndView Listar(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        mv.addObject("movimiento", new Movimientoalmacen());

        List<Tipoitem> tipoitem = repo5.findTipoitemEntities();

        // String fechaEmision = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        //request.setAttribute("fecha", fechaEmision);
        mv.addObject("tipoitem", tipoitem);
        mv.setViewName("MovimientoAlmacen");

        return mv;
    }

    @RequestMapping(value = "crearmovimiento.htm", method = RequestMethod.POST)

    public ModelAndView NuevoCliente(HttpServletRequest request) throws Exception {

        int idOrden = Integer.parseInt(request.getParameter("idOrdenCompra"));
        String tipomovimiento = request.getParameter("tipoMovimiento");
        String fecha = request.getParameter("fecha");
        int idtipoitem = Integer.parseInt(request.getParameter("idTipoItem"));
        
        String detalles = request.getParameter("detalles");

        Movimientoalmacen mov = new Movimientoalmacen();
        mov.setDetallemovimientoList(new ArrayList<Detallemovimiento>());

        mov.setIdOrdenCompra(repo1.findOrdencompra(idOrden));
        mov.setTipoMovimiento(tipomovimiento);
        mov.setFecha(fecha);
        mov.setIdTipoItem(repo5.findTipoitem(idtipoitem));

        StringTokenizer stD = new StringTokenizer(detalles, ";");
        int detalles0 = stD.countTokens();
        String descripcion = "";
        int cantidad = 0;

        for (int i = 0; i < detalles0; i++) {
            // Obtenemos los datos de cada pedido
            String linea = stD.nextToken();
            StringTokenizer stDatos = new StringTokenizer(linea, ",");
            cantidad = Integer.parseInt(stDatos.nextToken());
            descripcion = stDatos.nextToken();
            

            Detallemovimiento m = new Detallemovimiento();

            m.setCantidad(cantidad);
            m.setDescripcion(descripcion);
            mov.getDetallemovimientoList().add(m);
        }

        repo.create(mov);

        List<Pedido> pedido = repo3.findPedidoEntities();

        int d = 5;

        for (Pedido p : pedido) {
            if (mov.getIdTipoItem().getIdTipoItem() == 2 && mov.getIdOrdenCompra().getIdPedido().getIdPedido() == p.getIdPedido()) {
                p.setIdEstado(repo6.findEstadopedido(d));
                repo3.edit(p);
            }

        }

        return new ModelAndView("redirect:/listamovimiento.htm");
    }

    @RequestMapping("listamovimiento.htm")

    public ModelAndView ListaMovimiento(Model model) {

        ModelAndView mv = new ModelAndView();

        List<Movimientoalmacen> movi = repo.findMovimientoalmacenEntities();

        model.addAttribute("movi", movi);

        mv.setViewName("listamovimiento");

        return mv;

    }
    /*   
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
     */

}
