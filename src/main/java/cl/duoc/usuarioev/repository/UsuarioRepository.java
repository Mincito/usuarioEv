package cl.duoc.usuarioev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.duoc.usuarioev.model.Usuarios;


@Repository

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    
    List<Usuarios> findByRol(String rol);    
    
}
