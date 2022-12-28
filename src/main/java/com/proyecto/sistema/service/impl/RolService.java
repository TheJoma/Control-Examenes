package com.proyecto.sistema.service.impl;

import com.proyecto.sistema.dto.RolDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.mapper.RolMapper;
import com.proyecto.sistema.model.Rol;
import com.proyecto.sistema.repository.IRolRespository;
import com.proyecto.sistema.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRolService {

    @Autowired
    private IRolRespository rolRespository;

    @Autowired
    private RolMapper mapper;


    @Override
    public Rol guardarRol(RolDTO rolDTO) throws ResourceFoundException {
        Rol rol = mapper.rolDTOtoRol(rolDTO);
        rol.setName(rolDTO.getName().toUpperCase());
        if(rolRespository.existsByName(rol.getName()))
            throw new ResourceFoundException("El rol ya existe");
        return rolRespository.save(rol);
    }

    @Override
    public Rol actualizarRol(RolDTO rolDTO) throws ResourceFoundException, ResourceNotFoundException {
        Rol rol = mapper.rolDTOtoRol(rolDTO);
        rol.setName(rolDTO.getName().toUpperCase());
        if(rolRespository.existsByName(rol.getName()) && rolRespository.findByName(rol.getName()).get().getId() != rol.getId())
            throw  new ResourceFoundException("El rol ya existe");
        if(rol.getId() == null)
            throw  new ResourceNotFoundException("Rol no encontrado");
        return rolRespository.save(rol);
    }

    @Override
    public Rol eliminarRol(Long id) throws ResourceNotFoundException {
        Rol rol = rolRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rol no encontrado"));
        rolRespository.delete(rol);
        return rol;
    }

    @Override
    public List<RolDTO> listarRoles() {
        return mapper.rolsToRolDTOS(rolRespository.findAll());
    }

    @Override
    public Rol obtenerRol(String nombre) throws ResourceNotFoundException {
        return rolRespository.findByName(nombre).orElseThrow(()->new ResourceNotFoundException("Rol no encontrado"));
    }
}
