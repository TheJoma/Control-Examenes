package com.proyecto.sistema.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {

    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String perfil;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal() {
    }

    public UsuarioPrincipal(String username, String password, String nombre, String apellido, String email, String telefono, String perfil, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.perfil = perfil;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        Collection<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol ->
                new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getUsername(),
                usuario.getPassword(),usuario.getNombre(),usuario.getApellido(),
                usuario.getEmail(),usuario.getTelefono(),usuario.getPerfil(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPerfil() {
        return perfil;
    }

    @Override
    public String toString() {
        return "UsuarioPrincipal{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", perfil='" + perfil + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}/*implements UserDetails {

    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String perfil;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal() {
    }

    public UsuarioPrincipal(String username, String password, String nombre, String apellido, String email, String telefono, String perfil, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.perfil = perfil;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(
                rol.getName().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getUsername(),
                usuario.getPassword(),usuario.getNombre(),usuario.getApellido(),
                usuario.getEmail(),usuario.getTelefono(),usuario.getPerfil(),authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }*/

