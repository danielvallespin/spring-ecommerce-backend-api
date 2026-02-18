package com.dani.spring.ecommerce_backend_api.entities;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Objeto role de la tabla roles")
@Table(name="roles")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del rol", example = "ROLE_USER")
    @IsRequired
    private String name;

    public Role(){}

    public Role(@NotBlank String name) {
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