package cl.km.service;

import java.util.List;
import cl.km.clases.Producto;
import org.springframework.data.jpa.repository.Query;

public interface ProductoService {

    public List<Producto> listarProducto();

    public void guardar(Producto producto);

    public void eliminar(Producto producto);

    public Producto encontrarProducto(Producto producto);

    @Query("SELECT p FROM Producto p ORDER BY p.precio DESC")
    List<Producto> obtenerProductosOrdenadosPorPrecioDescendente();

}
