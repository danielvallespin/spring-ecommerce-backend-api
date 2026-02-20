package com.dani.spring.ecommerce_backend_api.dto;

import java.math.BigDecimal;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.NumberRange;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;

import io.swagger.v3.oas.annotations.media.Schema;

public class FullProductRequestDto {

    // Datos del producto

    @Schema(description = "Nombre del producto", example = "PS5")
    @IsRequired
    @StringSize(min=3, max=45)
    private String name;

    @Schema(description = "Descripcion del producto", example = "Consola de videojuegos")
    @IsRequired
    @StringSize(min=5, max=150)
    private String description;

    @Schema(description = "Precio del producto", example = "499.95")
    @IsRequired
    @NumberRange(min=0)
    private BigDecimal price;

    @Schema(description = "Cantidad de stock del producto", example = "20")
    @IsRequired
    @NumberRange(min=0)
    private Integer stock;

    @Schema(description = "url de la imagen a mostrar del producto", example = "/images/products/test.jpg")
    @IsRequired
    private String imageUrl;

    @Schema(description = "Campo que indica si el campo es visible o no", example = "true")
    private Boolean visible;

    // Datos del detail

    @Schema(description = "Descripción extendida del producto", example = "Video consola de ultima generacion capaz de ejecutar juegos con una resolucion de 4k y una tasa de 120Hz")
    @StringSize(min = 10)
    @IsRequired
    private String longDescription;

    @Schema(description = "Marca del producto", example = "Sony")
    @IsRequired
    private String brand;

    @Schema(description = "Categorias a las que pertenece el produto", example = "electronica,gaming")
    @IsRequired
    private String categories;

    public FullProductRequestDto(){}

    public FullProductRequestDto(String name, String description, BigDecimal price, Integer stock, String imageUrl,
            Boolean visible, String longDescription, String brand, String categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.visible = visible;
        this.longDescription = longDescription;
        this.brand = brand;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean isVisible() {
        return visible;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategories() {
        return categories;
    }



}
