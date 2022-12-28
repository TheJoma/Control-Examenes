package com.proyecto.sistema.repository;

import com.proyecto.sistema.model.Curso;
import com.proyecto.sistema.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExamenRepository extends JpaRepository<Examen,Long> {

    boolean existsById(Long id);

    @Query("SELECT CASE WHEN COUNT(p)>0 THEN 'FALSE' ELSE 'TRUE' END AS id FROM Examen p WHERE p.id = :id")
    boolean notExistsById(@Param("id") Long id);

    List<Examen> findByActivo(Boolean estado);

    List<Examen> findByCursoAndActivo(Curso curso,Boolean activo);
}
