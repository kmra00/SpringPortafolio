package cl.km.clases;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    //valida String
    @NotEmpty
    private String nombre;
    private String descripcion;
    private String marca;
    @NotNull // valida numericos
    private int stock;
    @NotNull
    private int precio;
}
