package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.NumberRange;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ProductFromCartRequestDto {

    @Schema(description = "Id del producto", example = "4")
    @NotNull
    @NumberRange(min=0)
    private Long productId;

    public ProductFromCartRequestDto() {
    }

    public Long getProductId() {
        return productId;
    }

}
