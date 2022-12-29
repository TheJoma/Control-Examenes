package com.proyecto.sistema.controller;

import com.proyecto.sistema.dto.CursoDTO;
import com.proyecto.sistema.exceptions.MessageDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.model.Curso;
import com.proyecto.sistema.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    @Autowired
    private ICursoService cursoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<MessageDTO> guardar(@Valid @RequestBody CursoDTO cursoDTO) throws ResourceFoundException {
        Curso curso = cursoService.guardarCurso(cursoDTO);
        String message = "El curso "+curso.getName()+" ha sido guardado con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.CREATED,message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/")
    public  ResponseEntity<MessageDTO> actualizarCurso(@Valid @RequestBody CursoDTO cursoDTO) throws ResourceFoundException, ResourceNotFoundException {
        Curso curso  = cursoService.actualizarCurso(cursoDTO);
        String message = "El curso "+curso.getName()+" ha sido actualizado con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROFESOR','ALUMNO')")
    @GetMapping("/")
    public ResponseEntity<Set<CursoDTO>> listarCursos(){
        return ResponseEntity.ok(cursoService.listarCursos());
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO>  eliminarCurso(@PathVariable(name = "id") Long id) throws Exception {
        Curso curso = cursoService.eliminarCurso(id);
        String message = "El Curso "+curso.getName()+" ha sido eliminado con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ALUMNO','ROLE_PROFESOR')")
    @GetMapping("/obtener/{id}")
    public ResponseEntity<CursoDTO> obtenerCurso(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(cursoService.obtenerCurso(id));
    }
}
