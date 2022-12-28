package com.proyecto.sistema.mapper;

import com.proyecto.sistema.dto.PreguntaDTO;
import com.proyecto.sistema.model.Pregunta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ExamenMapper.class})
public interface PreguntaMapper {



    @Mapping(source = "examen",target = "examen")
    Pregunta preguntaDTOToPregunta(PreguntaDTO preguntaDTO);

    @Mapping(target = "respuestaDada",ignore = true)
    @Mapping(source = "examen",target = "examen")
    PreguntaDTO preguntaToPreguntaDTO(Pregunta pregunta);

    List<PreguntaDTO> preguntasToPreguntaDTOS(List<Pregunta> preguntas);
}

