package com.dani.spring.ecommerce_backend_api.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public class PaymentMethodResponseDto {

    @Schema(description = "Id del metodo de pago", example = "4")
    private final Long paymentId;

    @Schema(description = "Id del usuario", example = "3")
    private final Long userId;

    @Schema(description = "Tipo de tarjeta", example = "visa")
    private final String type;

    @Schema(description = "Ultimos 4 digitos", example = "1234")
    private final String last4;

    @Schema(description = "Mes de expiracion", example = "10")
    private final Integer expiryMonth;

    @Schema(description = "Año de expiracion", example = "2028")
    private final Integer expiryYear;

    @Schema(description = "Indicado de tarjeta principal", example = "true")
    private final boolean isDefault;

    @Schema(description = "Indicador de tarjeta habilitada", example = "true")
    private final boolean enabled;

    public PaymentMethodResponseDto(Long paymentId, Long userId, String type, String last4, Integer expiryMonth, Integer expiryYear, boolean isDefault, boolean enabled) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.type = type;
        this.last4 = last4;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.isDefault = isDefault;
        this.enabled = enabled;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public String getLast4() {
        return last4;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public boolean isEnabled() {
        return enabled;
    }


}
