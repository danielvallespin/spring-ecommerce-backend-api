package com.dani.spring.ecommerce_backend_api.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public class CartItemResponseDto {

    
    private final ProductCartOrWishResponseDto product;

    @Schema(description = "Canmtidad", example = "3")
    private final Integer quantity;

    public CartItemResponseDto(ProductCartOrWishResponseDto product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductCartOrWishResponseDto getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    

}
