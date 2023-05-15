package cl.km.webController;

import cl.km.clases.Producto;
import lombok.extern.slf4j.Slf4j;
import cl.km.service.ProductoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Esta clase es responsable de controlar las peticiones relacionadas con los
 * productos.
 */
@Controller
@Slf4j // para visualizar los logs
public class ControladorProducto {

    @Autowired
    private ProductoService productoService;

    /**
     * Método que se encarga de manejar la petición GET al inicio de la
     * aplicación.
     *
     * @param model objeto para añadir atributos para su uso en la vista.
     * @param user objeto que representa al usuario que ha iniciado sesión.
     * @return la vista "index".
     */
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var productos = productoService.listarProducto();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login" + user);
        model.addAttribute("productos", productos);
        var precioTotal = 0;
        for (var p : productos) {
            precioTotal += p.getPrecio();
        }
        model.addAttribute("precioTotal", precioTotal);

        return "index";
    }

    /**
     * Método que se encarga de manejar la petición GET para agregar un
     * producto.
     *
     * @param producto objeto de tipo Producto que se utiliza para el binding de
     * datos.
     * @return la vista "modificarProducto".
     */
    @GetMapping("/agregar")
    public String agregar(Producto producto) {
        return "modificarProducto";
    }

    /**
     * Método que se encarga de manejar la petición POST para guardar un
     * producto.
     *
     * @param producto objeto de tipo Producto que se utiliza para el binding de
     * datos.
     * @param errores objeto que encapsula los errores de validación.
     * @return una redirección a la vista "index" en caso de éxito o la vista
     * "modificarProducto" en caso de error.
     */
    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, Errors errores) {
        if (errores.hasErrors()) {
            return "modificarProducto";
        }
        productoService.guardar(producto);
        return "redirect:/";
    }

    /**
     * Método que se encarga de manejar la petición GET para editar un producto.
     *
     * @param producto objeto de tipo Producto que se utiliza para el binding de
     * datos.
     * @param model objeto para añadir atributos para su uso en la vista.
     * @return la vista "modificarProducto".
     */
    @GetMapping("/editar/{idProducto}")
    public String editar(Producto producto, Model model) {
        producto = productoService.encontrarProducto(producto);
        model.addAttribute("producto", producto);
        return "modificarProducto";
    }

    /**
     *
     * Controlador encargado de la eliminación de un producto por su id.
     *
     * @param producto El producto a eliminar.
     * @return Una cadena que representa la dirección de redirección después de
     * eliminar el producto.
     */
    @GetMapping("/eliminar")
    public String eliminar(Producto producto) {
        productoService.eliminar(producto);
        return "redirect:/";
    }

    /**
     *
     * Controlador encargado de mostrar la página de contacto.
     *
     * @return Una cadena que representa la vista de la página de contacto.
     */
    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

}
