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

@Schema(description = "Objeto producto de la tabla products")
@Table(name="products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

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
    private boolean visible;

    public Product(){}

    public Product(String name, String description, BigDecimal price, Integer stock, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public Product(String name, String description, BigDecimal price, Integer stock, String imageUrl, boolean visible) {
        this(name, description, price, stock, imageUrl);
        this.visible = visible;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }



}
