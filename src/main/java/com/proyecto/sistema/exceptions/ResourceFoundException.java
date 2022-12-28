package com.proyecto.sistema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ResourceFoundException extends Exception{

    public ResourceFoundException(String message) {
        super(message);
    }
}
