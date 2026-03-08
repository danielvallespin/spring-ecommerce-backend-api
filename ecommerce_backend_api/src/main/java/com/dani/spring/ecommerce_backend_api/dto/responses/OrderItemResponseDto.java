package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class OrderItemResponseDto {

    @Schema(description = "Id del producto", example = "5")
    private final Long productId;

    @Schema(description = "Nombre del poducto", example = "Television") 
    private final String name;

    @Schema(description = "Descripcion del producto", example = "SmartTV 55'")  
    private final String description;

    @Schema(description = "Url de la imagen del producto", example = "/images/products/image.jpg")
    private final String imageUrl;

    @Schema(description = "Marca del producto", example = "Sony")
    private final String brand;

    @Schema(description = "Precio de cuando se compro el producto", example = "234.56")
    private final BigDecimal price;

    @Schema(description = "Cantidad del producto adquirida", example = "2")
    private final Integer quantity;

    public OrderItemResponseDto(Long productId, String name, String description, String imageUrl, String brand, BigDecimal price, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    

}
