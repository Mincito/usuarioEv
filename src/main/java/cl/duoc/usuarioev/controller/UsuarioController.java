package cl.duoc.usuarioev.controller;

import java.util.List;

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
    public List<Usuarios> getAllUsuarios() {
        return this.usuarioService.getAllUsuarios();
    }

    @PostMapping
    public Usuarios guardarUsuario(@Valid @RequestBody CreateUsuarioRequest request) {
        Usuarios usuario = UsuarioMapper.toModel(request);
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuarios getById(@PathVariable Integer id) {
        return this.usuarioService.getById(id);
    }

    @PutMapping("/{id}")
    public Usuarios updateUsuario(@PathVariable Integer id, @Valid @RequestBody UpdateUsuarioRequest request) {
        Usuarios usuario = UsuarioMapper.toModel(id, request);
        return this.usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable int id) {
        return this.usuarioService.deleteUsuario(id);
    }

    @GetMapping("/total")
    public int totalUsuarios() {
        return this.usuarioService.totalUsuarios();
    }

    @GetMapping("/rol/{rol}")
    public List<Usuarios> obtenerPorRol(@PathVariable String rol) {
        return this.usuarioService.obtenerPorRol(rol);
    }
}