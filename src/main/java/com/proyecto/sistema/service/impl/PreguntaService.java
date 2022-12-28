package com.proyecto.sistema.service.impl;
import com.proyecto.sistema.dto.PreguntaDTO;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.mapper.ExamenMapper;
import com.proyecto.sistema.mapper.PreguntaMapper;
import com.proyecto.sistema.model.Examen;
import com.proyecto.sistema.model.Pregunta;
import com.proyecto.sistema.repository.IPreguntaRepository;
import com.proyecto.sistema.service.IPreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PreguntaService implements IPreguntaService {

    @Autowired
    private IPreguntaRepository preguntaRepository;

    @Autowired
    private PreguntaMapper mapper;
    @Autowired
    private ExamenMapper mapperExamen;

    @Override
    public Pregunta guardarPregunta(PreguntaDTO preguntaDTO) {
        Pregunta pregunta = mapper.preguntaDTOToPregunta(preguntaDTO);
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Pregunta actualizarPregunta(PreguntaDTO preguntaDTO) throws ResourceNotFoundException {
        Pregunta pregunta = mapper.preguntaDTOToPregunta(preguntaDTO);
        if(preguntaRepository.notExistsById(preguntaDTO.getId()))
            throw new ResourceNotFoundException("Error al actualizar pregunta");
        return preguntaRepository.save(pregunta);
    }

    @Override
    public List<PreguntaDTO> listarPreguntas() {
        return mapper.preguntasToPreguntaDTOS(preguntaRepository.findAll());
    }

    @Override
    public void eliminarPregunta(Long id) throws ResourceNotFoundException {
        Pregunta pregunta = preguntaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pregunta no encontrada") );
        preguntaRepository.delete(pregunta);
    }

    @Override
    public PreguntaDTO obtenerPregunta(Long id) throws ResourceNotFoundException {
        return preguntaRepository.findById(id).map(mapper::preguntaToPreguntaDTO).orElseThrow(()->new ResourceNotFoundException("Pregunta no encontrada"));
        //return mapper.preguntaToPreguntaDTO(preguntaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pregunta no encontrada")));
    }

    @Override
    public List<PreguntaDTO> listarPreguntasPorExamen(Long id) {
        Examen examen = new Examen();
        examen.setId(id);
        return mapper.preguntasToPreguntaDTOS(preguntaRepository.findByExamen(examen));
    }


}
