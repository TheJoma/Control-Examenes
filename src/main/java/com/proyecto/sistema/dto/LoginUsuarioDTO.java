package com.proyecto.sistema.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUsuarioDTO {
    @NotBlank(message = "es necesario")
    private String username;
    @NotBlank(message = "es necesario")
    @Size(min = 3)
    private String password;

    public LoginUsuarioDTO() {
    }

    public LoginUsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginUsuarioDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
