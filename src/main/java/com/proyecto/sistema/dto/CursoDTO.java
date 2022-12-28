package com.proyecto.sistema.dto;

import javax.validation.constraints.NotBlank;
/*@Setter
@Getter*/
public class CursoDTO {

    private Long id;


    @NotBlank(message = "es necesario")
    private String name;

    @NotBlank(message = "es necesario")
    private String descripcion;

    public CursoDTO() {
    }

    public CursoDTO(Long id, String name, String descripcion) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
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
}
