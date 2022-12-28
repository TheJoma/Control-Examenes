package com.proyecto.sistema.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
@Setter
@Getter*/
public class PreguntaDTO {

    private Long id;
    @NotBlank(message = "es necesario")
    @Size(min = 1, message = "invalido")
    private String contenido;
    private String imagen;
    @NotBlank
    @Size(min = 1,message = "invalido")
    private String opcion1;
    @NotBlank
    @Size(min = 1,message = "invalido")
    private String opcion2;
    @NotBlank
    @Size(min = 1,message = "invalido")
    private String opcion3;
    @NotBlank
    @Size(min = 1,message = "invalido")
    private String opcion4;
    @NotBlank(message = "requerida")
    @Size(min = 1,message = "invalida")
    private String respuesta;

    private String respuestaDada;
    private ExamenDTO examen;

    public PreguntaDTO() {
    }

    public PreguntaDTO(Long id, String contenido, String imagen, String opcion1, String opcion2, String opcion3, String opcion4, String respuesta, ExamenDTO examen) {
        this.id = id;
        this.contenido = contenido;
        this.imagen = imagen;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.respuesta = respuesta;
        this.examen = examen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public ExamenDTO getExamen() {
        return examen;
    }

    public void setExamen(ExamenDTO examen) {
        this.examen = examen;
    }

    public String getRespuestaDada() {
        return respuestaDada;
    }

    public void setRespuestaDada(String respuestaDada) {
        this.respuestaDada = respuestaDada;
    }
}
