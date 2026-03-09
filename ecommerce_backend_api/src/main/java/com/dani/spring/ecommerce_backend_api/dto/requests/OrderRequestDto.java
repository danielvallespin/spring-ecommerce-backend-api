package com.dani.spring.ecommerce_backend_api.dto.requests;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class OrderRequestDto {

    @NotNull
    private List<OrderPaymentRequestDto> payments;

    public OrderRequestDto(@NotNull List<OrderPaymentRequestDto> payments) {
        this.payments = payments;
    }

    public List<OrderPaymentRequestDto> getPayments() {
        return payments;
    }

    public void setPayments(List<OrderPaymentRequestDto> payments) {
        this.payments = payments;
    }


}
