package com.proyecto.sistema.service.impl;

import com.proyecto.sistema.dto.ExamenDTO;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.mapper.CursoMapper;
import com.proyecto.sistema.mapper.ExamenMapper;
import com.proyecto.sistema.model.Curso;
import com.proyecto.sistema.model.Examen;
import com.proyecto.sistema.repository.ICursoRepository;
import com.proyecto.sistema.repository.IExamenRepository;
import com.proyecto.sistema.service.IExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamenService implements IExamenService {
    @Autowired
    private IExamenRepository examenRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private ExamenMapper mapper;
    @Autowired
    private CursoMapper mapperCurso;


    @Override
    public Examen guardarExamen(ExamenDTO examenDTO) {
        Examen examen = mapper.examenDTOToExamen(examenDTO);
        if(cursoRepository.findById(examen.getCurso().getId()).get() == null){
            System.out.println("no se encontro curso");
        }
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(ExamenDTO examenDTO) throws ResourceNotFoundException {
        Examen examen = mapper.examenDTOToExamen(examenDTO);
        if(examenRepository.notExistsById(examenDTO.getId()))
            throw new ResourceNotFoundException("Examen no encontrado");
        return examenRepository.save(examen);
    }

    @Override
    public List<ExamenDTO> listarExamenes() {
        return mapper.examensTOExamenDTOS(examenRepository.findAll());
    }
    @Override
    public ExamenDTO obtenerExamen(Long id) throws ResourceNotFoundException {
        return examenRepository.findById(id).map(mapper::examenTOExamenDTO).orElseThrow(()-> new ResourceNotFoundException("Examen no encontado"));
       //return mapper.examenTOExamenDTO(examenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El examen no existe")));
    }

    @Override
    public Examen obtenerExamen2(Long id) throws ResourceNotFoundException {
        return examenRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Examen no encontrado"));
    }

    @Override
    public Examen eliminarExamen(Long id) throws ResourceNotFoundException {
        Examen examen = examenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El examen no existe"));
        examenRepository.delete(examen);
        return examen;
    }

    @Override
    public List<ExamenDTO> listarExamenesActivos() {
        return mapper.examensTOExamenDTOS(examenRepository.findByActivo(true));
    }

    @Override
    public List<ExamenDTO> listarExamenesActivosPorCurso(Long id) {
        Curso curso = new Curso();
        curso.setId(id);
        return mapper.examensTOExamenDTOS(examenRepository.findByCursoAndActivo(curso,true));
    }
}
