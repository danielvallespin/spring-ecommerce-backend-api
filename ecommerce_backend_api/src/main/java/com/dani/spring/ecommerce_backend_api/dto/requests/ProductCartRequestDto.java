package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.NumberRange;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ProductCartRequestDto {

    @Schema(description = "Id del producto", example = "4")
    @NotNull
    @NumberRange(min=1) 
    private Long productId;

    @Schema(description = "Cantidad", example = "3")
    @NotNull
    @NumberRange(min=0)
    private Integer quantity;

    public ProductCartRequestDto() {
    }

    public ProductCartRequestDto(@NotNull Long productId, @NotNull Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    

}
