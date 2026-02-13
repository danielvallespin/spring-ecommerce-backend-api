package com.dani.spring.ecommerce_backend_api.entities;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name="products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del producto", example = "PS5")
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min=4, max=45, message = "El nombre debe tener entre 4 y 45 caracteres")
    private String name;

    @Schema(description = "Descripcion del producto", example = "Consola de videojuegos")
    @NotBlank(message = "La descripción del producto es obligatoria")
    @Size(min=5, max=150, message = "La descripción debe tener entre 5 y 150 caracteres")
    private String description;

    @Schema(description = "Precio del producto", example = "499.95")
    @NotNull(message = "El precio del producto es obligatorio")
    @Min(value=0, message = "El precio debe ser mayor o igual a 0")
    private BigDecimal price;

    @Schema(description = "Cantidad de stock del producto", example = "20")
    @NotNull(message = "La cantidad del producto es obligatoria")
    @Min(value=0, message = "El precio debe ser mayor o igual a 0")
    private Integer stock;

    public Product(){}

    public Product(@NotBlank String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


}
