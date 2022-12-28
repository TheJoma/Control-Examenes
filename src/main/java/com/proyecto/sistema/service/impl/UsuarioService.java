package com.proyecto.sistema.service.impl;

import com.proyecto.sistema.dto.JwtTokenDTO;
import com.proyecto.sistema.dto.LoginUsuarioDTO;
import com.proyecto.sistema.dto.UsuarioDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.mapper.UsuarioMapper;
import com.proyecto.sistema.model.Rol;
import com.proyecto.sistema.model.Usuario;
import com.proyecto.sistema.repository.IRolRespository;
import com.proyecto.sistema.repository.IUsuarioRespository;
import com.proyecto.sistema.security.JwtProvider;
import com.proyecto.sistema.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRespository usuarioRespository;

    @Autowired
    private IRolRespository rolRespository;
    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Usuario guardarUsuarioAdmin(UsuarioDTO usuarioDTO) throws ResourceFoundException {
        if(usuarioRespository.existsByUsername(usuarioDTO.getUsername()))
            throw new ResourceFoundException("Username ya existe");
        if(usuarioRespository.existsByEmail(usuarioDTO.getEmail()))
            throw new ResourceFoundException("Email ya existe");

        Rol rol = rolRespository.findByName("ROLE_ADMIN").get();

        Usuario usuario = mapper.usuarioDTOToUsuario(usuarioDTO);
        usuario.getRoles().add(rol);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        return usuarioRespository.save(usuario);
    }

    @Override
    public Usuario guardarUsuarioProfesor(UsuarioDTO usuarioDTO) throws ResourceFoundException {
        if(usuarioRespository.existsByUsername(usuarioDTO.getUsername()))
            throw new ResourceFoundException("Username ya existe");
        if(usuarioRespository.existsByEmail(usuarioDTO.getEmail()))
            throw new ResourceFoundException("Email ya existe");

        Rol rol = rolRespository.findByName("ROLE_PROFESOR").get();
        Usuario usuario = mapper.usuarioDTOToUsuario(usuarioDTO);
        usuario.getRoles().add(rol);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        return usuarioRespository.save(usuario);
    }

    @Override
    public Usuario guardarUsuarioAlumno(UsuarioDTO usuarioDTO) throws ResourceFoundException {
        if(usuarioRespository.existsByUsername(usuarioDTO.getUsername()))
            throw new ResourceFoundException("Username ya existe");
        if(usuarioRespository.existsByEmail(usuarioDTO.getEmail()))
            throw new ResourceFoundException("Email ya existe");

        Rol rol = rolRespository.findByName("ROLE_ALUMNO").get();
        Usuario usuario = mapper.usuarioDTOToUsuario(usuarioDTO);
        usuario.getRoles().add(rol);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        return usuarioRespository.save(usuario);
    }

    @Override
    public JwtTokenDTO login(LoginUsuarioDTO loginUsuarioDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioDTO.getUsername(),loginUsuarioDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtTokenDTO(token);
    }

    @Override
    public UsuarioDTO obtenerUsuario(String username) throws ResourceNotFoundException {
        Usuario usuario = usuarioRespository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return mapper.usuarioToUsuarioDTO(usuario);
    }

    @Override
    public Usuario actualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.usuarioDTOToUsuario(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        System.out.println(usuario);
        return usuarioRespository.save(usuario);
    }
    @Override
    public List<UsuarioDTO> listarPorRol(Rol rol) {
        return mapper.usuariosToUsuariosDTOS(usuarioRespository.findByRoles(rol));
    }

    @Override
    public Usuario eliminarUsuario(Long id) throws ResourceNotFoundException {
        Usuario usuario = usuarioRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
        usuarioRespository.delete(usuario);
        return usuario;
    }

}
