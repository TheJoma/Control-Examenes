package com.proyecto.sistema.dto;

import javax.validation.constraints.NotBlank;

public class RolDTO {

    private Long id;
    @NotBlank(message = "es necesario")
    private String name;

    public RolDTO() {
    }

    public RolDTO(String name) {
        this.name = name;
    }

    public RolDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
