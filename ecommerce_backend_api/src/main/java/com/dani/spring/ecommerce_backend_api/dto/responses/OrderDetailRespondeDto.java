package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.util.List;

public class OrderDetailRespondeDto {

    private final OrderResponseDto order;

    private final List<OrderItemResponseDto> items;

    private final List<OrderPaymentResponseDto> payments;

    public OrderDetailRespondeDto(OrderResponseDto order, List<OrderItemResponseDto> items, List<OrderPaymentResponseDto> payments) {
        this.order = order;
        this.items = items;
        this.payments = payments;
    }

    public OrderResponseDto getOrder() {
        return order;
    }

    public List<OrderItemResponseDto> getItems() {
        return items;
    }

    public List<OrderPaymentResponseDto> getPayments() {
        return payments;
    }

    

}
