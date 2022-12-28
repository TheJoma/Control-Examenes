package com.proyecto.sistema.repository;

import com.proyecto.sistema.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICursoRepository extends JpaRepository<Curso,Long> {
    boolean existsByName(String name);
    Optional<Curso> findByName(String name);
}
