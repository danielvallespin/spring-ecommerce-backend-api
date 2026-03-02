package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.NumberRange;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AddOrDelWishlistItemRequestDto {

    @Schema(description = "Id dela lista", example = "5")
    @NotNull
    @NumberRange(min=0)
    private long wishlistId;

    @Schema(description = "Id del producto", example = "1")
    @NotNull
    @NumberRange(min=0)
    private long productId;

    public AddOrDelWishlistItemRequestDto(){
    }

    public long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    

}
