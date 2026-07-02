package cl.duoc.usuarioev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;

import cl.duoc.usuarioev.dto.CreateUsuarioRequest;
import cl.duoc.usuarioev.dto.UpdateUsuarioRequest;
import cl.duoc.usuarioev.mapper.UsuarioMapper;
import cl.duoc.usuarioev.model.Usuarios;
import cl.duoc.usuarioev.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Usuarios",
    description = "Operaciones de gestión de usuarios"
)
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
        summary = "Listar usuarios",
        description = "Da una lista de todos los usuarios del sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de usuarios obtenida correctamente"
    )
    

    @GetMapping
    public ResponseEntity<List<Usuarios>> getAllUsuarios() {
        List<Usuarios> usuarios = this.usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }


    @Operation(
        summary = "Registrar usuario",
        description = "Crea un usuario con el rol de ADMIN, COMPRADOR o PESCADOR"
    )
    @ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Usuario creado correctamente"
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados no son correctos"
    )
    })

    

@PostMapping
public ResponseEntity<Usuarios> guardarUsuario(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos necesarios para registrar un usuario",
            required = true,
            content = @Content(
                schema = @Schema(implementation = CreateUsuarioRequest.class),
                examples = @ExampleObject(
                    name = "Ejemplo de usuario pescador",
                    value = """
                    {
                      "nombre": "Juan Pinilla",
                      "correo": "juan.pinilla@correo.cl",
                      "contrasena": "123456",
                      "rol": "PESCADOR",
                      "telefono": "987654321",
                      "activo": true
                    }
                    """
                )
            )
        )
        @Valid @RequestBody CreateUsuarioRequest request) {

    Usuarios usuario = UsuarioMapper.toModel(request);
    Usuarios usuarioGuardado = this.usuarioService.guardarUsuario(usuario);

    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }


    @Operation(
        summary = "Buscar usuario por ID",
        description = "Obtiene un usuario según su identificador numérico"
    )
    @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Usuario encontrado"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "Usuario no encontrado"
    )
    })

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getById(@PathVariable Integer id) {
        Usuarios usuario = this.usuarioService.getById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

@Operation(
    summary = "Actualizar usuario",
    description = "Modifica los datos de un usuario existente"
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Usuario actualizado correctamente"
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados no son correctos"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "Usuario no encontrado"
    )
})
    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> updateUsuario(
        @PathVariable Integer id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos actualizados del usuario",
            required = true,
            content = @Content(
                schema = @Schema(implementation = UpdateUsuarioRequest.class),
                examples = @ExampleObject(
                    name = "Ejemplo de actualización",
                    value = """
                    {
                      "nombre": "Juan Pérez Actualizado",
                      "correo": "juan.perez@correo.cl",
                      "contrasena": "123456",
                      "rol": "PESCADOR",
                      "telefono": "912345678",
                      "activo": true
                    }
                    """
                )
            )
        )
        @Valid @RequestBody UpdateUsuarioRequest request) {

    Usuarios usuarioExistente = this.usuarioService.getById(id);

    if (usuarioExistente == null) {
        return ResponseEntity.notFound().build();
    }

    Usuarios usuario = UsuarioMapper.toModel(id, request);
    Usuarios usuarioActualizado = this.usuarioService.updateUsuario(usuario);

    return ResponseEntity.ok(usuarioActualizado);
    }


    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario según su identificador"
    )
    @ApiResponses({
    @ApiResponse(
        responseCode = "204",
        description = "Usuario eliminado correctamente"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "Usuario no encontrado"
    )
    })


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        Usuarios usuario = this.usuarioService.getById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        this.usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Obtener total de usuarios",
        description = "Devuelve la cantidad total de usuarios registrados"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Total de usuarios obtenido correctamente"
    )

    @GetMapping("/total")
    public ResponseEntity<Integer> totalUsuarios() {
        int total = this.usuarioService.totalUsuarios();
        return ResponseEntity.ok(total);
    }


    @Operation(
        summary = "Buscar usuarios por rol",
        description = "Obtiene todos los usuarios que tengan el rol indicado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Usuarios obtenidos correctamente"
    )


    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Usuarios>> obtenerPorRol(@PathVariable String rol) {
        List<Usuarios> usuarios = this.usuarioService.obtenerPorRol(rol);
        return ResponseEntity.ok(usuarios);
    }


    @Operation(
        summary = "Buscar usuario por correo",
        description = "Obtiene un usuario mediante su correo electrónico"
    )
    @ApiResponses({
        
    @ApiResponse(
        responseCode = "200",
        description = "Usuario encontrado"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "Usuario no encontrado"
    )
    })

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Usuarios> obtenerPorCorreo(@PathVariable String correo) {
        Usuarios usuario = this.usuarioService.obtenerPorCorreo(correo);

    if (usuario == null) {
        return ResponseEntity.notFound().build();
    } return ResponseEntity.ok(usuario);
    }
}