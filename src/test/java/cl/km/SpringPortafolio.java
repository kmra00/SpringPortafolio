package cl.km;

import cl.km.clases.Producto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringPortafolio {

	@Test
	void contextLoads() {
	}
        
    @Test
    public void testSetNombre() {
        Producto producto = new Producto();
        producto.setNombre("Producto de prueba");
        assertEquals("Producto de prueba", producto.getNombre());
    }

  

    @Test
    public void testSetPrecio() {
        Producto producto = new Producto();
        producto.setPrecio(100);
        assertEquals(100, (int) producto.getPrecio());
    }

}
