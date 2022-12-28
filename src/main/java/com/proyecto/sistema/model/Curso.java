package com.proyecto.sistema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
/*
@Setter
@Getter*/
@Entity
@Table(name = "tb_cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "curso")
    @JsonIgnore
    private Set<Examen> lstExamenes = new LinkedHashSet<>();

    public Curso() {
    }

    public Curso(Long id, String name, String descripcion, Set<Examen> lstExamenes) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.lstExamenes = lstExamenes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Examen> getLstExamenes() {
        return lstExamenes;
    }

    public void setLstExamenes(Set<Examen> lstExamenes) {
        this.lstExamenes = lstExamenes;
    }
}
