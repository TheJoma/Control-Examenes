package com.proyecto.sistema.controller;

import com.proyecto.sistema.dto.JwtTokenDTO;
import com.proyecto.sistema.dto.LoginUsuarioDTO;
import com.proyecto.sistema.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> login(@Valid @RequestBody LoginUsuarioDTO loginUsuarioDTO){
        JwtTokenDTO jwtTokenDTO = usuarioService.login(loginUsuarioDTO);
        return  ResponseEntity.ok(jwtTokenDTO);
    }
}
