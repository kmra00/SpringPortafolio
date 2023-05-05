package cl.km.webController;

import cl.km.clases.Producto;
import lombok.extern.slf4j.Slf4j;
import cl.km.service.ProductoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorProducto {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var productos = productoService.listarProducto();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login" + user);
        model.addAttribute("productos", productos);
        var precioTotal=0;
        for(var p: productos){precioTotal+=p.getPrecio();}
        model.addAttribute("precioTotal",precioTotal);
        
        return "index";
    }
    

    @GetMapping("/agregar")
    public String agregar(Producto producto) {
        return "modificarProducto";
    }

    @PostMapping("/guardar")
    // validacion de objeto producto
    public String guardar(@Valid Producto producto, Errors errores) {
        if (errores.hasErrors()) {
            return "modificarProducto";
        }
        productoService.guardar(producto);
        return "redirect:/";
    }

    // crea instancia de producto y lo setea automatico conjunto con model 
    @GetMapping("/editar/{idProducto}")
    public String editar(Producto producto, Model model) {
        producto = productoService.encontrarProducto(producto);
        model.addAttribute("producto", producto);
        return "modificarProducto";
    }

    //ya reconoce el id de la clase producto
    @GetMapping("/eliminar")
    public String eliminar(Producto producto) {
        productoService.eliminar(producto);
        return "redirect:/";
    }


}
