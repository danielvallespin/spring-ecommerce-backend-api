package com.dani.spring.ecommerce_backend_api.dto.requests;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.validations.NumberRange;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class OrderRequestDto {

    @NotNull
    private List<OrderPaymentRequestDto> payments;

    @Schema(description = "Id de la direccion de envio", example = "2")
    @NumberRange(min=1)
    @NotNull
    private Long addressId;

    public OrderRequestDto(List<OrderPaymentRequestDto> payments, Long addressId) {
        this.payments = payments;
        this.addressId = addressId;
    }

    public List<OrderPaymentRequestDto> getPayments() {
        return payments;
    }

    public void setPayments(List<OrderPaymentRequestDto> payments) {
        this.payments = payments;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }



}
