package com.proyecto.sistema.service;

import com.proyecto.sistema.dto.RolDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.model.Rol;

import java.util.List;

public interface IRolService {
    public Rol guardarRol(RolDTO rolDTO) throws ResourceFoundException;

    public Rol actualizarRol(RolDTO rolDTO) throws ResourceFoundException, ResourceNotFoundException;

    public Rol eliminarRol(Long id) throws ResourceNotFoundException;

    public List<RolDTO> listarRoles();

    public Rol obtenerRol(String nombre) throws ResourceNotFoundException;
}
