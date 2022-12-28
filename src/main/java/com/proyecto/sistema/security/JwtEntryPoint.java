package com.proyecto.sistema.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.sistema.exceptions.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        logger.error("token no encontrado o invalido");
        //res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
        MessageDTO messageDTO = new MessageDTO(HttpStatus.UNAUTHORIZED,"Token no encontrado o invalido");
        res.setContentType("application/json");
        res.setStatus(messageDTO.getHttpStatus().value());
        res.getWriter().write(new ObjectMapper().writeValueAsString(messageDTO));
        res.getWriter().flush();
        res.getWriter().close();
    }
}
