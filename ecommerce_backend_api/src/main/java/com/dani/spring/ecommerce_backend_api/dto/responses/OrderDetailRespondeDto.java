package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.util.List;

public class OrderDetailRespondeDto {

    private final OrderResponseDto order;

    private final String fullShippingAddress;

    private final String shippingCountry;

    private final String shippingCity;

    private final String shippingPostalCode;

    private final List<OrderItemResponseDto> items;

    private final List<OrderPaymentResponseDto> payments;

    

    public OrderDetailRespondeDto(OrderResponseDto order, String fullShippingAddress, String shippingCountry, String shippingCity, String shippingPostalCode, List<OrderItemResponseDto> items, List<OrderPaymentResponseDto> payments) {
        this.order = order;
        this.fullShippingAddress = fullShippingAddress;
        this.shippingCountry = shippingCountry;
        this.shippingCity = shippingCity;
        this.shippingPostalCode = shippingPostalCode;
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

    public String getFullShippingAddress() {
        return fullShippingAddress;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    

}
