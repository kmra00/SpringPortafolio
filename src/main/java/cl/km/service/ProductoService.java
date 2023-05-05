package cl.km.service;

import java.util.List;
import cl.km.clases.Producto;

public interface ProductoService {
    
    public List<Producto> listarProducto();
    
    public void guardar(Producto producto);
    
    public void eliminar(Producto producto);
    
    public Producto encontrarProducto(Producto producto);

    
}
