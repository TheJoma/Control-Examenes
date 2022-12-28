package com.proyecto.sistema.service;

import com.proyecto.sistema.dto.CursoDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.model.Curso;
import java.util.Set;

public interface ICursoService {

    public Curso guardarCurso(CursoDTO cursoDTO) throws ResourceFoundException;
    public Curso actualizarCurso(CursoDTO cursoDTO) throws ResourceFoundException, ResourceNotFoundException;
    public Set<CursoDTO> listarCursos();
    public CursoDTO obtenerCurso(Long id) throws ResourceNotFoundException;
    public Curso eliminarCurso(Long id) throws ResourceNotFoundException;

}
