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

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuarios>> getAllUsuarios() {
        List<Usuarios> usuarios = this.usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuarios> guardarUsuario(@Valid @RequestBody CreateUsuarioRequest request) {
        Usuarios usuario = UsuarioMapper.toModel(request);
        Usuarios usuarioGuardado = this.usuarioService.guardarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getById(@PathVariable Integer id) {
        Usuarios usuario = this.usuarioService.getById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> updateUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUsuarioRequest request) {

        Usuarios usuarioExistente = this.usuarioService.getById(id);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        Usuarios usuario = UsuarioMapper.toModel(id, request);
        Usuarios usuarioActualizado = this.usuarioService.updateUsuario(usuario);

        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        Usuarios usuario = this.usuarioService.getById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        this.usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalUsuarios() {
        int total = this.usuarioService.totalUsuarios();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Usuarios>> obtenerPorRol(@PathVariable String rol) {
        List<Usuarios> usuarios = this.usuarioService.obtenerPorRol(rol);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Usuarios> obtenerPorCorreo(@PathVariable String correo) {
        Usuarios usuario = this.usuarioService.obtenerPorCorreo(correo);

    if (usuario == null) {
        return ResponseEntity.notFound().build();
    } return ResponseEntity.ok(usuario);
    }
}