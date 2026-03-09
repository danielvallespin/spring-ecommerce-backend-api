package com.dani.spring.ecommerce_backend_api.dto.requests;

import java.math.BigDecimal;

import com.dani.spring.ecommerce_backend_api.validations.NumberRange;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class OrderPaymentRequestDto {

    @Schema(description = "Id del metodo de pago", example = "4")
    @NotNull
    @NumberRange(min=1)
    private Long paymentId;

    @Schema(description = "Cantidad a pagar con el metodo de pago", example = "123.45")
    @NotNull
    @NumberRange(min=0)
    private BigDecimal amount;

    public OrderPaymentRequestDto(Long paymentId, BigDecimal amount) {
        this.paymentId = paymentId;
        this.amount = amount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
