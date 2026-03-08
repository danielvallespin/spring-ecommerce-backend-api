package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class SimpleProductDto {

    @Schema(description = "Id del producto", example = "5")
    private final Long productId;

    @Schema(description = "Nombre del poducto", example = "Television") 
    private final String name;

    @Schema(description = "Descripcion del producto", example = "SmartTV 55'")  
    private final String description;

    @Schema(description = "Precio del producto", example = "329.99")
    private final BigDecimal price;

    @Schema(description = "Cantidad disponible del producto", example = "22")
    private final Integer stock;

    @Schema(description = "Url de la imagen del producto", example = "/images/products/image.jpg")
    private final String imageUrl;

    @Schema(description = "Campo que indica la visibilidad del producto", example = "true")
    private final boolean visible;

    public SimpleProductDto(Long productId, String name, String description, BigDecimal price, Integer stock, String imageUrl, boolean visible) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.visible = visible;
    }

    public Long getProductId() {
        return productId;
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

    public boolean isVisible() {
        return visible;
    }

    


}
