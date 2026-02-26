package com.dani.spring.ecommerce_backend_api.dto.responses;

public class CartItemResponseDto {

    private ProductCartOrWishResponseDto product;

    private Integer quantity;

    public CartItemResponseDto(ProductCartOrWishResponseDto product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductCartOrWishResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductCartOrWishResponseDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    

}
