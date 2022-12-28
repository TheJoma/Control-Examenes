package com.proyecto.sistema.mapper;

import com.proyecto.sistema.dto.ExamenDTO;
import com.proyecto.sistema.model.Examen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CursoMapper.class})
public interface ExamenMapper {

    @Mapping(source = "curso",target = "curso")
    Examen examenDTOToExamen(ExamenDTO examenDTO);
    @Mapping(source = "curso",target = "curso")
    ExamenDTO examenTOExamenDTO(Examen examen);
    List<ExamenDTO> examensTOExamenDTOS(List<Examen> examenes);

}
