package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;
import java.util.List;

public class CartResponseDto {

    private Long cartId;

    private Long userId;

    private BigDecimal amount;

    private List<CartItemResponseDto> items;

    public CartResponseDto(Long cartId, Long userId, List<CartItemResponseDto> items) {
        this.cartId = cartId;
        this.userId = userId;
        this.items = items;
        this.calculateAmount();
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<CartItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponseDto> items) {
        this.items = items;
    }


    private void calculateAmount(){
        BigDecimal result = BigDecimal.ZERO;
        if (this.items != null && !items.isEmpty()){
            for(CartItemResponseDto itemResponse : items){
                BigDecimal price = itemResponse.getProduct().getPrice();
                Integer quantity = itemResponse.getQuantity();
                result = result.add(price.multiply(BigDecimal.valueOf(quantity)));
            }
        }
        this.amount = result;
    }


}
