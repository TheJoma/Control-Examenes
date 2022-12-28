package com.proyecto.sistema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageDTO> throwNotFoundException(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(HttpStatus.NOT_FOUND,e.getMessage()));
    }
    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<MessageDTO> throwAttributeException(AttributeException e){
        return ResponseEntity.badRequest().body(new MessageDTO(HttpStatus.BAD_REQUEST,e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO> generalException(Exception e){
        return ResponseEntity.internalServerError().body(new MessageDTO(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO> validationException(MethodArgumentNotValidException e){
        List<String> messages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(
                (err) -> messages.add( ((FieldError) err).getField() +" "+  err.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(new MessageDTO(HttpStatus.BAD_REQUEST,OperationUtils.timBrackets(messages.toString())));
    }

    @ExceptionHandler(ResourceFoundException.class)
    public ResponseEntity<MessageDTO> throwFoundException(ResourceFoundException e){
        return ResponseEntity.status(HttpStatus.FOUND).body(new MessageDTO(HttpStatus.FOUND,e.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageDTO> badCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(HttpStatus.NOT_FOUND,"credenciales incorrectas"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageDTO> accessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageDTO(HttpStatus.FORBIDDEN,"Acceso denegado al recurso"));

    }
}
