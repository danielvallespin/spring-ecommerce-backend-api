package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class OrderPaymentResponseDto {

    @Schema(description = "Id del metodo de pago", example = "6")
    private final Long paymentId;

    @Schema(description = "Ultimos 4 digitos del metodo de pago", example = "1234")
    private final String last4;

    @Schema(description = "Cantidad pagada con ese metodo", example = "123.45")
    private final BigDecimal cardAmount;

    public OrderPaymentResponseDto(Long paymentId, String last4, BigDecimal cardAmount) {
        this.paymentId = paymentId;
        this.last4 = last4;
        this.cardAmount = cardAmount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getLast4() {
        return last4;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }


}
