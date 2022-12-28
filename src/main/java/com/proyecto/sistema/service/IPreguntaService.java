package com.proyecto.sistema.service;

import com.proyecto.sistema.dto.PreguntaDTO;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.model.Pregunta;

import java.util.List;

public interface IPreguntaService {

    public Pregunta guardarPregunta(PreguntaDTO preguntaDTO);
    public Pregunta actualizarPregunta(PreguntaDTO preguntaDTO) throws ResourceNotFoundException;
    public List<PreguntaDTO> listarPreguntas();
    public void eliminarPregunta(Long id) throws ResourceNotFoundException;
    public PreguntaDTO obtenerPregunta(Long id) throws ResourceNotFoundException;

    public List<PreguntaDTO> listarPreguntasPorExamen(Long id);


}
