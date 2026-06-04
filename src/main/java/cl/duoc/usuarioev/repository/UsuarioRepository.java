package cl.duoc.usuarioev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cl.duoc.usuarioev.model.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    @Query(value = "SELECT * FROM tabla_usuarios WHERE rol = :rol", nativeQuery = true)
    List<Usuarios> selectPorRol(@Param("rol") String rol);

    @Query(value = "SELECT * FROM tabla_usuarios WHERE correo = :correo", nativeQuery = true)
    Optional<Usuarios> selectPorCorreo(@Param("correo") String correo);


    default int totalUsuarios() {
        return (int) this.count();
    }
}