package cl.duoc.usuarioev.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUsuarioRequest(

    @NotBlank(message = "El nombre no puede estar vacío")
    String nombre,

    @NotBlank(message = "El correo no puede estar vacío")
    String correo,

    @NotBlank(message = "La clave no puede estar vacía")
    String contrasena,

    @NotBlank(message = "El rol no puede ser inexistente")
    String rol,

    @NotBlank(message = "Tiene que haber un teléfono")
    String telefono,

    boolean activo

) {
}