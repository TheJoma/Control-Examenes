package com.proyecto.sistema.dto;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "es necesario")
    private String username;
    @NotBlank(message = "es necesario")
    @Size(min = 3,message = "minimo debe tener 3 digitos")
    private String password;
    @NotBlank(message = "es necesario")
    private String nombre;
    @NotBlank(message = "es necesario")
    private String apellido;
    @NotBlank
    @Email(message = "es invalido")
    private String email;
    @NotBlank
    @Pattern(regexp = "[9][0-9]{8}",message = "debe empezar con 9 y tiene que tener 9 digitos")
    private String telefono;
    private String perfil;
    //@NotEmpty
    private Set<RolDTO> roles = new HashSet<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String username, String password, String nombre, String apellido, String email, String telefono, String perfil, Set<RolDTO> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.perfil = perfil;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Set<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolDTO> roles) {
        this.roles = roles;
    }
}
