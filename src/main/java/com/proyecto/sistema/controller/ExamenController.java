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

    @PostMapping("/")
    public ResponseEntity<MessageDTO> guardarExamen(@Valid @RequestBody ExamenDTO examenDTO){
        Examen examen = examenService.guardarExamen(examenDTO);
        String message = "Examen " + examen.getTitulo() + " ha sido guardado";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.CREATED,message));
    }

    @PutMapping("/")
    public ResponseEntity<MessageDTO> actualizarExamen(@Valid @RequestBody ExamenDTO examenDTO) throws ResourceNotFoundException {
        Examen examen = examenService.actualizarExamen(examenDTO);
        String message = "Examen " + examen.getTitulo() + " ha sido actualizado";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    @GetMapping("/")
    public ResponseEntity<List<ExamenDTO>> listarExamenes(){
        return ResponseEntity.ok(examenService.listarExamenes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> eliminarExamen(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        Examen examen = examenService.eliminarExamen(id);
        String message = "El examen " +examen.getTitulo()+" ha sido eliminado";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
        //return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<ExamenDTO> obtenerExamen(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(examenService.obtenerExamen(id));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ExamenDTO>> listarExamenesActivos(){
        return ResponseEntity.ok(examenService.listarExamenesActivos());
    }

    //esto esta en observacion porque es por examen y se pone un objeto y no un id qye llama a servicio para que sea un objeto y usarlo
    @GetMapping("/activoscurso/{id}")
    public ResponseEntity<List<ExamenDTO>> listarExamenesActivosPorCurso(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(examenService.listarExamenesActivosPorCurso(id));
    }
}
