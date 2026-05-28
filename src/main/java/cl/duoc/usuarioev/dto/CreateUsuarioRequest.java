package cl.duoc.usuarioev.dto;

import jakarta.validation.constraints.NotBlank;


public record CreateUsuarioRequest(

    @NotBlank(message = "Nombre no puede ser vacío")
    String nombre,

    @NotBlank(message = "Correo no puede ser vacío")
    String correo,

    @NotBlank(message = "Contraseña no puede ser vacía")
    String contrasena,

    @NotBlank(message = "Rol no puede ser vacío")
    String rol,

    @NotBlank(message = "Teléfono no puede ser vacío")
    String telefono,

    boolean activo

) {
}