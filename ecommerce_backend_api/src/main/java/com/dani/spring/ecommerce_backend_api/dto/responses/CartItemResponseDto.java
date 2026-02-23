package com.dani.spring.ecommerce_backend_api.dto.responses;

public class CartItemResponseDto {

    private ProductCartResponseDto product;

    private Integer quantity;

    public CartItemResponseDto(ProductCartResponseDto product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductCartResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductCartResponseDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    

}
