package com.proyecto.sistema.controller;

import com.proyecto.sistema.dto.RolDTO;
import com.proyecto.sistema.exceptions.MessageDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.model.Rol;
import com.proyecto.sistema.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private IRolService rolService;

    @PostMapping("/")
    public ResponseEntity<MessageDTO> guardarRol(@Valid @RequestBody RolDTO rolDTO) throws ResourceFoundException {
        Rol rol = rolService.guardarRol(rolDTO);
        String message = "Rol "+ rol.getName()+" ha sido guardado";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.CREATED,message));
    }

    @PutMapping("/")
    public ResponseEntity<MessageDTO> actualizarRol(@Valid @RequestBody RolDTO rolDTO) throws ResourceFoundException, ResourceNotFoundException {
        Rol rol = rolService.actualizarRol(rolDTO);
        String message = "Rol " + rol.getName() + " ha sido actualizado";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> eliminarRol(@PathVariable(name = "id")Long id) throws ResourceNotFoundException {
        Rol rol = rolService.eliminarRol(id);
        String message = "Rol " + rol.getName() + " ha siado eliimnado";
        return  ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    @GetMapping("/")
    public ResponseEntity<List<RolDTO>> listarRoles(){
        return ResponseEntity.ok(rolService.listarRoles());
    }
}
