package com.proyecto.sistema.service.impl;

import com.proyecto.sistema.dto.CursoDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.mapper.CursoMapper;
import com.proyecto.sistema.model.Curso;
import com.proyecto.sistema.repository.ICursoRepository;
import com.proyecto.sistema.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private CursoMapper mapper;

    @Override
    public Curso guardarCurso(CursoDTO cursoDTO) throws ResourceFoundException {
        if(cursoRepository.existsByName(cursoDTO.getName()))
            throw new ResourceFoundException("El Curso ya existe");
        Curso curso = mapper.cursoDTOToCurso(cursoDTO);
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizarCurso(CursoDTO cursoDTO) throws ResourceFoundException, ResourceNotFoundException {
        Curso curso = mapper.cursoDTOToCurso(cursoDTO);
        if(cursoRepository.existsByName(cursoDTO.getName()) && cursoRepository.findByName(cursoDTO.getName()).get().getId() != cursoDTO.getId())
            throw new ResourceFoundException("El Curso ya existe");
        if(cursoDTO.getId() == null)
            throw  new ResourceNotFoundException("Curso no encontrado");
        return cursoRepository.save(curso);

    }

    @Override
    public Set<CursoDTO> listarCursos() {
        return mapper.cursosToCursosDTOS(new LinkedHashSet<>(cursoRepository.findAll()));
    }


    @Override
    public CursoDTO obtenerCurso(Long id) throws ResourceNotFoundException {
        return cursoRepository.findById(id).map(mapper::cursoToCursoDTO).orElseThrow(()-> new ResourceNotFoundException("Curso no encontrado"));
        //return mapper.cursoToCursoDTO(cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado")));
    }

    @Override
    public Curso eliminarCurso(Long id) throws ResourceNotFoundException {
        Curso curso = cursoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Curso no encontrado"));
        cursoRepository.delete(curso);
        return curso;
    }
}
