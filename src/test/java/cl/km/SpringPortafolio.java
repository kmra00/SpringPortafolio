package cl.km;

import cl.km.clases.Producto;
import org.junit.Rule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringPortafolio {
     private Producto producto;

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
    
   @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testSetPrecioInvalido() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("El precio debe ser mayor que 0");
        Integer precio = 0;
        producto.setPrecio(precio);
    }



    

}
