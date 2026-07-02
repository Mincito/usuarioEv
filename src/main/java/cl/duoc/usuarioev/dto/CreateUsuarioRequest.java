package cl.duoc.usuarioev.dto;

import cl.duoc.usuarioev.model.UsuarioRol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUsuarioRequest(

    @NotBlank(message = "El nombre no puede estar vacío")
    String nombre,

    @NotBlank(message = "El correo no puede estar vacío")
    String correo,

    @NotBlank(message = "La contraseña no puede estar vacía")
    String contrasena,

    @NotNull(message = "El rol no puede estar vacío")
    UsuarioRol rol,

    @NotBlank(message = "El teléfono no puede estar vacío")
    String telefono,

    boolean activo

) {
}