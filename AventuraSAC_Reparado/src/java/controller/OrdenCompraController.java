package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.controllers.EmpleadoJpaController;
import model.controllers.OrdencompraDetalleJpaController;
import model.controllers.OrdencompraJpaController;
import model.controllers.ProveedorJpaController;
import model.entities.Cotizacion;
import model.entities.Empleado;
import model.entities.Ordencompra;
import model.entities.OrdencompraDetalle;
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
    private EmpleadoJpaController repo3;

    
    public OrdenCompraController() {
        em = getEntityManager();
        repo = new OrdencompraJpaController(emf);
        repo1 = new OrdencompraDetalleJpaController(emf);
        repo2 = new ProveedorJpaController(emf);
        repo3 = new EmpleadoJpaController(emf);
    }

    private EntityManager getEntityManager() {
        
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("AventuraSAC_ReparadoPU");
        }
        return emf.createEntityManager();
    }
    @RequestMapping(value = "OrdenCompra.htm", method = RequestMethod.GET)
    
    public ModelAndView NuevaOrdenCompra(Model model, HttpServletRequest request) {
        
        
        /*int idproveedor = Integer.parseInt(request.getParameter("id"));*/
        
        Empleado e = (Empleado) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", e);
        
        ModelAndView mv = new ModelAndView();
        
        List<Proveedor> proveedor = repo2.findProveedorEntities();
        List<Proveedor> prov = new ArrayList();
        
        mv.addObject("proveedor" , proveedor); 
        /*
        for(Proveedor p : proveedor){
            if(p.getIdProveedor() == idproveedor){
                prov.add(p);
            }
        }
        
        
        mv.addObject("datos" , prov); */
        model.addAttribute("ordencompra", new Ordencompra());/*Guiaremision es la clase osea la entidad*/
        mv.setViewName("OrdenCompra");
        
        return mv;
    }
    
    @RequestMapping(value = "generarorden.htm", method = RequestMethod.POST)
    
    public ModelAndView NuevaFichaTecnica(Model model, HttpServletRequest request) throws Exception{
        
        Empleado e = (Empleado) request.getSession().getAttribute("usuario");

        int id = Integer.parseInt(request.getParameter("idEmpleado"));
        int idproveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String fecha = request.getParameter("fechaEmision");
        String fechae = request.getParameter("fechaEntrega");
        String detalles = request.getParameter("detalles");

        Ordencompra o = new Ordencompra();
        o.setOrdencompraDetalleList(new ArrayList<OrdencompraDetalle>());
        
        o.setIdEmpleado(repo3.findEmpleado(id));
        o.setIdProveedor(repo2.findProveedor(idproveedor));
        o.setFechaEmision(fecha);
        o.setFechaEntrega(fechae);
          
        StringTokenizer stD = new StringTokenizer(detalles, ";");
        int detalles0 = stD.countTokens();
        //int idDetalleCompra =0;
        String descripcion = "";
        int cantidad = 0;

        for (int i = 0; i < detalles0; i++) {
            // Obtenemos los datos de cada pedido
            String linea = stD.nextToken();
            StringTokenizer stDatos = new StringTokenizer(linea, ",");
            descripcion = stDatos.nextToken();
            cantidad = Integer.parseInt(stDatos.nextToken());

            //OrdencompraDetalle pedidoDet = repo1.findOrdencompraDetalle(idDetalleCompra);

            OrdencompraDetalle detalleO = new OrdencompraDetalle();
            detalleO.setItem(0);
            detalleO.setIdOrdenCompra(o);
            detalleO.setDescripcion(descripcion);
            detalleO.setCantidad(cantidad);
            o.getOrdencompraDetalleList().add(detalleO);

        }

        repo.create(o);

        
        return new ModelAndView("redirect:/menulogistica.htm");
    }
    
}
