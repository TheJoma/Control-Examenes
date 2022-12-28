package com.proyecto.sistema.service;

import com.proyecto.sistema.dto.ExamenDTO;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.model.Examen;

import java.util.List;

public interface IExamenService {

    public Examen guardarExamen(ExamenDTO examenDTO);
    public Examen actualizarExamen(ExamenDTO examenDTO) throws ResourceNotFoundException;
    public List<ExamenDTO> listarExamenes();
    public ExamenDTO obtenerExamen(Long id) throws ResourceNotFoundException;

    public Examen obtenerExamen2(Long id) throws ResourceNotFoundException;
    public Examen eliminarExamen(Long id) throws ResourceNotFoundException;

    public List<ExamenDTO> listarExamenesActivos();

    public List<ExamenDTO> listarExamenesActivosPorCurso(Long id);
}
