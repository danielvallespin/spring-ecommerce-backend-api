package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductCartOrWishResponseDto {

    @Schema(description = "Id de producto", example = "3")
    private final Long productId;

    @Schema(description = "Nombre del prodcuto", example = "Raton Logitech")
    private final String name;

    @Schema(description = "Descripcion del prodcuto", example = "Raton gaming")
    private final String description;

    @Schema(description = "Precio del prodcuto", example = "68.99")
    private final BigDecimal price;

    @Schema(description = "Url de la imagen del producto", example = "/images/products/image.jpg")
    private final String imageUrl;

    @Schema(description = "Marca del producto", example = "Logitech")
    private final String brand;

    public ProductCartOrWishResponseDto(Long productId, String name, String description, BigDecimal price, String imageUrl, String brand) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.brand = brand;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    

}
