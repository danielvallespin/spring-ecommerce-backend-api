package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.util.List;

public class WishlistResponseDto {

    private final Long id;

    private final Long userId;

    private final String name;

    private final List<ProductCartOrWishResponseDto> items;

    public WishlistResponseDto(Long id, Long userId, String name, List<ProductCartOrWishResponseDto> items) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.items = items;
    }

    public Long getId() {
        return id;
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
