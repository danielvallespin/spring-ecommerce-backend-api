package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class WishlistResponseDto {

    @Schema(description = "Id de la wishlist", example = "5")
    private final Long wishlistId;

    @Schema(description = "Id del usuario", example = "2")
    private final Long userId;

    @Schema(description = "Nombre de la wishlist", example = "Gaming")
    private final String name;

    private final List<ProductCartOrWishResponseDto> items;

    public WishlistResponseDto(Long wishlistId, Long userId, String name, List<ProductCartOrWishResponseDto> items) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.name = name;
        this.items = items;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<ProductCartOrWishResponseDto> getItems() {
        return items;
    }


}
