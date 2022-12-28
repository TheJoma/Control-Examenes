package com.proyecto.sistema.mapper;

import com.proyecto.sistema.dto.CursoDTO;
import com.proyecto.sistema.model.Curso;
import org.mapstruct.*;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    Curso cursoDTOToCurso(CursoDTO cursoDTO);
    CursoDTO cursoToCursoDTO(Curso curso);

    Set<CursoDTO> cursosToCursosDTOS(Set<Curso> curso);
}
