package com.proyecto.sistema.controller;

import com.proyecto.sistema.dto.PreguntaDTO;
import com.proyecto.sistema.exceptions.MessageDTO;
import com.proyecto.sistema.exceptions.ResourceNotFoundException;
import com.proyecto.sistema.mapper.PreguntaMapper;
import com.proyecto.sistema.model.Examen;
import com.proyecto.sistema.model.Pregunta;
import com.proyecto.sistema.service.IExamenService;
import com.proyecto.sistema.service.IPreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/pregunta")
public class PreguntaController {
    @Autowired
    private IPreguntaService preguntaService;

    @Autowired
    private IExamenService examenService;

    @Autowired
    private PreguntaMapper mapper;

    @PostMapping("/")
    public ResponseEntity<MessageDTO> guardarPregunta(@Valid @RequestBody PreguntaDTO preguntaDTO){
        preguntaService.guardarPregunta(preguntaDTO);
        return ResponseEntity.ok(new MessageDTO(HttpStatus.CREATED,"Pregunta registrada"));
    }

    @PutMapping("/")
    public ResponseEntity<MessageDTO> actualizarPregunta(@Valid @RequestBody PreguntaDTO preguntaDTO) throws ResourceNotFoundException {
        preguntaService.actualizarPregunta(preguntaDTO);
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,"Pregunta actualizada"));
    }

    @GetMapping("/")
    public ResponseEntity<List<PreguntaDTO>> listarPreguntas(){
        return ResponseEntity.ok(preguntaService.listarPreguntas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> eliminarPregunta(@PathVariable(name = "id")Long id) throws ResourceNotFoundException {
        preguntaService.eliminarPregunta(id);
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,"Pregunta eliminada"));
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<PreguntaDTO> obtenerPregunta(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(preguntaService.obtenerPregunta(id));
    }

    @GetMapping("/preguntasexamen/{id}")
    public ResponseEntity<List<PreguntaDTO>> obtenerPreguntasDelExamen(@PathVariable(name = "id")Long id) throws ResourceNotFoundException {
        Examen examen = examenService.obtenerExamen2(id);
        Set<Pregunta> preguntas = examen.getLstPreguntas();
        List lstPreguntas = new ArrayList(preguntas);
        if(lstPreguntas.size()>examen.getNumeroDePreguntas()){
            lstPreguntas = lstPreguntas.subList(0,examen.getNumeroDePreguntas()+1);
        }
        Collections.shuffle(lstPreguntas);
        return ResponseEntity.ok(lstPreguntas);
    }

    @GetMapping("/preguntasexamen2/{id}")
    public ResponseEntity<List<PreguntaDTO>> listarPreguntasPorExamen(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(preguntaService.listarPreguntasPorExamen(id));
    }

    @PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<PreguntaDTO> preguntasDto) throws ResourceNotFoundException {
        double puntosMaximos = 0;
        int respuestasCorrectas = 0;
        int intentos = 0;

        for(PreguntaDTO p :preguntasDto){
            Pregunta preg = mapper.preguntaDTOToPregunta(this.preguntaService.obtenerPregunta(p.getId()));
            if(preg.getRespuesta().equals(p.getRespuestaDada())){
                respuestasCorrectas ++;
                double puntos = (preguntasDto.get(0).getExamen().getPuntosMaximos()*1.0)/preguntasDto.size();
                puntosMaximos += puntos;
            }
            if(p.getRespuestaDada() != null){
                intentos ++;
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("puntosMaximos",puntosMaximos);
        map.put("respuestasCorrectas",respuestasCorrectas);
        map.put("intentos",intentos);

        return ResponseEntity.ok(map);
    }

}
