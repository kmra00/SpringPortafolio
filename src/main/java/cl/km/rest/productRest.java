package cl.km.rest;

import cl.km.clases.Producto;
import cl.km.dao.ProductoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class productRest {
    
    @Autowired
    private ProductoDao productoDao;
    @GetMapping
    public ResponseEntity<Producto> getProduct(){
    
        Producto prod = new Producto();
        prod.setIdProducto(1L);
        prod.setNombre("Producto 1");
        return ResponseEntity.ok(prod);
    }
}
