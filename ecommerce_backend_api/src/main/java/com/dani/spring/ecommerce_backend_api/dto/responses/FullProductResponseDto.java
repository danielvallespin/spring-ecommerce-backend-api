package com.dani.spring.ecommerce_backend_api.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public class FullProductResponseDto {

    // Datos del producto
    private final SimpleProductDto product;

    // Datos del detail
    @Schema(description = "Descripcion detalal del producto", example = "SmartTV 55' 4K 120Hz GoogleTv")
    private final String longDescription;

    @Schema(description = "Marca del producto", example = "Sony")
    private final String brand;

    @Schema(description = "Categorias a las que pertence el producto", example = "Electronica,Multimedia")
    private final String categories;

    public FullProductResponseDto(SimpleProductDto product, String longDescription, String brand, String categories) {
        this.product = product;
        this.longDescription = longDescription;
        this.brand = brand;
        this.categories = categories;
    }

    public SimpleProductDto getProduct() {
        return product;
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
