package cl.duoc.usuarioev.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.duoc.usuarioev.repository.UsuarioRepository;
import cl.duoc.usuarioev.model.Usuarios;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuarios> getAllUsuarios(){
        return usuarioRepository.findAll();
    }
    public Usuarios getById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuarios guardarUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    
    }
    public Usuarios updateUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }
    public String deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
        return "Usuario eliminado del sistema!";
    }
    public int totalUsuarios() {
    return usuarioRepository.totalUsuarios();
    }

    public List<Usuarios> obtenerPorRol(String rol) {
    return usuarioRepository.selectPorRol(rol.toUpperCase());
    }

    public Usuarios obtenerPorCorreo(String correo) {
        return usuarioRepository.selectPorCorreo(correo).orElse(null);
    }

}
