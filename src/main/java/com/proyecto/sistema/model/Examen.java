package com.proyecto.sistema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/*
@Setter
@Getter*/
@Entity
@Table(name = "tb_examenes")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private int puntosMaximos;
    private int numeroDePreguntas;
    private boolean activo = false;
    @ManyToOne(fetch = FetchType.EAGER)
    private Curso curso;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "examen")
    @JsonIgnore
    private Set<Pregunta> lstPreguntas = new HashSet<>();

    public Examen() {
    }

    public Examen(Long id, String titulo, String descripcion, int puntosMaximos, int numeroDePreguntas, boolean activo, Curso curso, Set<Pregunta> lstPreguntas) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.puntosMaximos = puntosMaximos;
        this.numeroDePreguntas = numeroDePreguntas;
        this.activo = activo;
        this.curso = curso;
        this.lstPreguntas = lstPreguntas;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Set<Pregunta> getLstPreguntas() {
        return lstPreguntas;
    }

    public void setLstPreguntas(Set<Pregunta> lstPreguntas) {
        this.lstPreguntas = lstPreguntas;
    }
}
