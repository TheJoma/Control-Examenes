package com.proyecto.sistema.service.impl;

import com.proyecto.sistema.model.Usuario;
import com.proyecto.sistema.model.UsuarioPrincipal;
import com.proyecto.sistema.repository.IUsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioRespository usuarioRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRespository.findByUsernameOrEmail(username,username);
        if(!usuario.isPresent())
            throw new UsernameNotFoundException("Username no existe");

        return UsuarioPrincipal.build(usuario.get());
    }
}
