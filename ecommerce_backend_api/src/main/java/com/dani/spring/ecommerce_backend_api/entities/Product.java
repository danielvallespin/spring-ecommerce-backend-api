package com.dani.spring.ecommerce_backend_api.entities;

import java.math.BigDecimal;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.NumberRange;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Table(name="products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del producto", example = "PS5")
    @IsRequired
    @StringSize(min=4, max=45)
    private String name;

    @Schema(description = "Descripcion del producto", example = "Consola de videojuegos")
    @IsRequired
    @StringSize(min=0, max=150)
    private String description;

    @Schema(description = "Precio del producto", example = "499.95")
    @IsRequired
    @NumberRange(min=0)
    private BigDecimal price;

    @Schema(description = "Cantidad de stock del producto", example = "20")
    @IsRequired
    @NumberRange(min=0)
    private Integer stock;

    @Schema(description = "url de la imagen a mostrar del producto", example = "test.jpg")
    @IsRequired
    private String imageUrl;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
