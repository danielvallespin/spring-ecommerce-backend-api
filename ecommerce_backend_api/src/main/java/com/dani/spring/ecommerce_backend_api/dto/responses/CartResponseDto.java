package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

public class CartResponseDto {

    @Schema(description = "Id del carrito", example = "4")
    private final Long cartId;

    @Schema(description = "Id del usuario", example = "6")
    private final Long userId;

    @Schema(description = "Precio total", example = "143.65")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private final BigDecimal amount;

    private final List<CartItemResponseDto> items;

    public CartResponseDto(Long cartId, Long userId, List<CartItemResponseDto> items, BigDecimal amount) {
        this.cartId = cartId;
        this.userId = userId;
        this.items = items;
        this.amount = amount;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<CartItemResponseDto> getItems() {
        return items;
    }


}
