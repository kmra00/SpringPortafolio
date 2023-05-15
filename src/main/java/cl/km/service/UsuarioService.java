package cl.km.service;

import cl.km.clases.Rol;
import cl.km.clases.Usuario;
import cl.km.dao.UsuarioDao;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j //para manejo de login
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    /**
     * Implementación de la interfaz UserDetailsService para cargar un usuario
     * por su nombre de usuario y retornar un objeto UserDetails para la
     * autenticación y autorización.
     *
     * @param username El nombre de usuario del usuario a cargar.
     * @return Un objeto UserDetails que representa al usuario cargado.
     * @throws UsernameNotFoundException Si no se encuentra un usuario con el
     * nombre de usuario proporcionado.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
