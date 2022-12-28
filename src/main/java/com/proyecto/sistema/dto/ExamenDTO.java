package com.proyecto.sistema.dto;

import javax.validation.constraints.*;
/*@Setter
@Getter*/
public class ExamenDTO {
    private Long id;
    @NotBlank(message = "es necesario")
    @Size(min = 1, max = 150, message = "excede la cantidad permitida")
    private String titulo;
    @NotBlank (message = "es necesario")
    private String descripcion;

    @NotNull @Min(1)
    private int puntosMaximos;

    @NotNull @Min(1)
    private int numeroDePreguntas;

    private boolean activo = false;
    private CursoDTO curso;
    public ExamenDTO() {
    }

    public ExamenDTO(Long id, String titulo, String descripcion, int puntosMaximos, int numeroDePreguntas, boolean activo, CursoDTO curso) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.puntosMaximos = puntosMaximos;
        this.numeroDePreguntas = numeroDePreguntas;
        this.activo = activo;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntosMaximos() {
        return puntosMaximos;
    }

    public void setPuntosMaximos(int puntosMaximos) {
        this.puntosMaximos = puntosMaximos;
    }

    public int getNumeroDePreguntas() {
        return numeroDePreguntas;
    }

    public void setNumeroDePreguntas(int numeroDePreguntas) {
        this.numeroDePreguntas = numeroDePreguntas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public CursoDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }
}
