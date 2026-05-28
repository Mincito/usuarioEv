package cl.duoc.usuarioev.mapper;

import cl.duoc.usuarioev.dto.CreateUsuarioRequest;
import cl.duoc.usuarioev.dto.UpdateUsuarioRequest;
import cl.duoc.usuarioev.model.Usuarios;

public class UsuarioMapper {

    public static Usuarios toModel(CreateUsuarioRequest request) {
        return new Usuarios(
                0,
                request.nombre(),
                request.correo(),
                request.contrasena(),
                request.rol(),
                request.telefono(),
                request.activo()
        );
    }

    public static Usuarios toModel(int id, UpdateUsuarioRequest request) {
        return new Usuarios(
                id,
                request.nombre(),
                request.correo(),
                request.contrasena(),
                request.rol(),
                request.telefono(),
                request.activo()
        );
    }
}