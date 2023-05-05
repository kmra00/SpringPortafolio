package cl.km.dao;

import cl.km.clases.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario,Long>{
    //recupera el objeto 
    Usuario findByUsername(String username);
}
