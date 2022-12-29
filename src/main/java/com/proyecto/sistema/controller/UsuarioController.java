package com.proyecto.sistema.controller;

import com.proyecto.sistema.dto.UsuarioDTO;
import com.proyecto.sistema.exceptions.MessageDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.model.Rol;
import com.proyecto.sistema.model.Usuario;
import com.proyecto.sistema.model.UsuarioPrincipal;
import com.proyecto.sistema.service.IRolService;
import com.proyecto.sistema.service.IUsuarioService;
import com.proyecto.sistema.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IRolService rolService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public String messageMethod(String name, String accion){
        return "Usuario " + name + " ha sido " +accion;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/guardar-profesor")
    public ResponseEntity<MessageDTO> guardarUsuarioProfesor(@Valid @RequestBody UsuarioDTO usuarioDTO) throws ResourceFoundException {
        Usuario usuario = usuarioService.guardarUsuarioProfesor(usuarioDTO);
        String message = "Usuario profesor " + usuario.getUsername() + " ha sido creado";
        return  ResponseEntity.ok(new MessageDTO(HttpStatus.CREATED,messageMethod(usuario.getNombre(),"ha sido creado")));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/guardar-alumno")
    public ResponseEntity<MessageDTO> guardarUsuarioAlumno(@Valid @RequestBody UsuarioDTO usuarioDTO) throws ResourceFoundException {
        Usuario usuario = usuarioService.guardarUsuarioAlumno(usuarioDTO);
        String message = "Usuario alumno " + usuario.getUsername() + " ha sido creado";
        return  ResponseEntity.ok(new MessageDTO(HttpStatus.CREATED,message));
    }

    @GetMapping("/usuario-actual")
    public UsuarioPrincipal obtenerUsuarioActual(Principal principal){
        return (UsuarioPrincipal) userDetailsService.loadUserByUsername(principal.getName());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ALUMNO','ROLE_PROFESOR')")
    @GetMapping("/obtener-usuario/{username}")
    public UsuarioDTO obtenerUsuario(@PathVariable(name = "username")String username) throws ResourceNotFoundException {
        return usuarioService.obtenerUsuario(username);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ALUMNO','ROLE_PROFESOR')")
    @PutMapping("/actualizar-usuario")
    public ResponseEntity<MessageDTO> actualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws ResourceFoundException, ResourceNotFoundException {
        Usuario usuario= usuarioService.actualizarUsuario(usuarioDTO);
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,messageMethod(usuario.getUsername(),"actualizado")));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> eliminarUsuario(@PathVariable(name = "id")Long id) throws ResourceNotFoundException {
        Usuario usuario = usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,messageMethod(usuario.getUsername(),"eliminado")));
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESOR')")
    @GetMapping("/listar-alumnos")
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosAlumno() throws ResourceNotFoundException {
        Rol rol = rolService.obtenerRol("ROLE_ALUMNO");
        return ResponseEntity.ok(usuarioService.listarPorRol(rol));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/listar-profesores")
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosProfesor() throws ResourceNotFoundException {
        Rol rol = rolService.obtenerRol("ROLE_PROFESOR");
        return ResponseEntity.ok(usuarioService.listarPorRol(rol));
    }


}
