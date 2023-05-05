package cl.km.service;

import java.util.List;
import cl.km.dao.ProductoDao;
import cl.km.clases.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//clase tipo service reconoce como contenedor para ser reconocida por el controlador
@Service
public class ProductoServiceImpl implements ProductoService {
    //instancia producto dao que conectara con producto dao con el autowired
    @Autowired
    private ProductoDao productoDao;
    
    //transactional debe ser tipo annotations
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProducto() {
        return (List<Producto>) productoDao.findAll();
    }
    //modificara la bd tipo commit no asi como un select 
    @Override
    @Transactional
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void eliminar(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto encontrarProducto(Producto producto) {
        //si no encuentra para no mandar error regresara null
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }
    

}
