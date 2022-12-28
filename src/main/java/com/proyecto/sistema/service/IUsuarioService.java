package com.proyecto.sistema.service;

import com.proyecto.sistema.dto.JwtTokenDTO;
import com.proyecto.sistema.dto.LoginUsuarioDTO;
import com.proyecto.sistema.dto.UsuarioDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.model.Rol;
import com.proyecto.sistema.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    public Usuario guardarUsuarioAdmin(UsuarioDTO usuarioDTO) throws ResourceFoundException;

    public Usuario guardarUsuarioProfesor(UsuarioDTO usuarioDTO) throws ResourceFoundException;

    public Usuario guardarUsuarioAlumno(UsuarioDTO usuarioDTO) throws ResourceFoundException;


    public JwtTokenDTO login(LoginUsuarioDTO loginUsuarioDTO);

    public UsuarioDTO obtenerUsuario(String username) throws ResourceNotFoundException;

    public Usuario actualizarUsuario(UsuarioDTO usuarioDTO);

    public List<UsuarioDTO> listarPorRol(Rol rol);

    public Usuario eliminarUsuario(Long id) throws ResourceNotFoundException;

}
