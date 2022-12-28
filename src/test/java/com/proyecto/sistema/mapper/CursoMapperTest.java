package com.proyecto.sistema.mapper;

import com.proyecto.sistema.dto.CursoDTO;
import com.proyecto.sistema.model.Curso;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class CursoMapperTest {

    @Autowired
    CursoMapper mapper;

    /*@Test
    public void cursoMapperTest(){
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setName("prueba");

        CursoDTO cursoDTO = mapper.cursoToCursoDTO(curso);
        System.out.println(curso);
        System.out.println(cursoDTO);

        assertEquals(curso.getId(),cursoDTO.getId());
    }*/
}
