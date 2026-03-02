package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.NumberRange;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AddOrModProductCartRequestDto {

    @Schema(description = "Id del producto a añadir", example = "12")
    @NotNull
    @NumberRange(min=0)
    private Long productId;

    @Schema(description = "Cantidad a añadir", example = "3")
    @NotNull
    @NumberRange(min=0)
    private Integer quantity;

    public AddOrModProductCartRequestDto() {
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
