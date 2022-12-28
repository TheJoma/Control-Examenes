package com.proyecto.sistema.mapper;

import com.proyecto.sistema.dto.UsuarioDTO;
import com.proyecto.sistema.model.Usuario;
import com.proyecto.sistema.model.UsuarioPrincipal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    List<UsuarioDTO> usuariosToUsuariosDTOS(List<Usuario> usuarios);

    UsuarioDTO princiapalToDTO(UsuarioPrincipal usuarioPrincipal);

}
