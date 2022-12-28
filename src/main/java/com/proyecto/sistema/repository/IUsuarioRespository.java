package com.proyecto.sistema.repository;

import com.proyecto.sistema.model.Rol;
import com.proyecto.sistema.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRespository extends JpaRepository<Usuario,Long> {

    boolean existsByUsername(String username);

    Optional<Usuario> findByUsername(String username);

    boolean existsByEmail(String email);

    List<Usuario> findByRoles(Rol rol);

    Usuario findByEmail(String email);
    Optional<Usuario> findByUsernameOrEmail(String username, String email);
}
