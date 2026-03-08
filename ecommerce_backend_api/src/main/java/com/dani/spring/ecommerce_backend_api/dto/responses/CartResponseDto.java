package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class CartResponseDto {

    @Schema(description = "Id del carrito", example = "4")
    private final Long cartId;

    @Schema(description = "Id del usuario", example = "6")
    private final Long userId;

    @Schema(description = "Precio total", example = "143.65")
    private final BigDecimal amount;

    private final List<CartItemResponseDto> items;

    public CartResponseDto(Long cartId, Long userId, List<CartItemResponseDto> items) {
        this.cartId = cartId;
        this.userId = userId;
        this.items = items;
        this.amount = calculateAmount();
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

    /**
     * Metodo que calcula el importe total del carrito
     * @return
     */
    private BigDecimal calculateAmount(){
        BigDecimal result = BigDecimal.ZERO;
        if (this.items != null && !items.isEmpty()){
            for(CartItemResponseDto itemResponse : items){
                BigDecimal price = itemResponse.getProduct().getPrice();
                Integer quantity = itemResponse.getQuantity();
                result = result.add(price.multiply(BigDecimal.valueOf(quantity)));
            }
        }

        return result;
    }


}
