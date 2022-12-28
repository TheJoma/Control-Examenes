package com.proyecto.sistema.repository;

import com.proyecto.sistema.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRespository extends JpaRepository<Rol,Long> {

    boolean existsByName(String name);

    Optional<Rol> findByName(String name);
}
