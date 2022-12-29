package com.proyecto.sistema.controller;


import com.proyecto.sistema.dto.ExamenDTO;
import com.proyecto.sistema.exceptions.MessageDTO;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.mapper.ExamenMapper;
import com.proyecto.sistema.model.Examen;
import com.proyecto.sistema.service.IExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/examen")
public class ExamenController {

    @Autowired
    private IExamenService examenService;

    @Autowired
    private ExamenMapper examenMapper;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESOR')")
    @PostMapping("/")
    public ResponseEntity<MessageDTO> guardarExamen(@Valid @RequestBody ExamenDTO examenDTO){
        Examen examen = examenService.guardarExamen(examenDTO);
        String message = "Examen " + examen.getTitulo() + " ha sido guardado";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.CREATED,message));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESOR')")
    @PutMapping("/")
    public ResponseEntity<MessageDTO> actualizarExamen(@Valid @RequestBody ExamenDTO examenDTO) throws ResourceNotFoundException {
        Examen examen = examenService.actualizarExamen(examenDTO);
        String message = "Examen " + examen.getTitulo() + " ha sido actualizado";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ALUMNO','ROLE_PROFESOR')")
    @GetMapping("/")
    public ResponseEntity<List<ExamenDTO>> listarExamenes(){
        return ResponseEntity.ok(examenService.listarExamenes());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PROFESOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> eliminarExamen(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        Examen examen = examenService.eliminarExamen(id);
        String message = "El examen " +examen.getTitulo()+" ha sido eliminado";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ALUMNO','ROLE_PROFESOR')")
    @GetMapping("/obtener/{id}")
    public ResponseEntity<ExamenDTO> obtenerExamen(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(examenService.obtenerExamen(id));
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ALUMNO','ROLE_PROFESOR')")
    @GetMapping("/activos")
    public ResponseEntity<List<ExamenDTO>> listarExamenesActivos(){
        return ResponseEntity.ok(examenService.listarExamenesActivos());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ALUMNO','ROLE_PROFESOR')")
    @GetMapping("/activoscurso/{id}")
    public ResponseEntity<List<ExamenDTO>> listarExamenesActivosPorCurso(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(examenService.listarExamenesActivosPorCurso(id));
    }
}
