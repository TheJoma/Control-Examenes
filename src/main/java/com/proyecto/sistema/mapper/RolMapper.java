package com.proyecto.sistema.mapper;

import com.proyecto.sistema.dto.RolDTO;
import com.proyecto.sistema.model.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol rolDTOtoRol(RolDTO rolDTO);

    RolDTO rolToRolDTO(Rol rol);

    List<RolDTO> rolsToRolDTOS(List<Rol> roles);
}
