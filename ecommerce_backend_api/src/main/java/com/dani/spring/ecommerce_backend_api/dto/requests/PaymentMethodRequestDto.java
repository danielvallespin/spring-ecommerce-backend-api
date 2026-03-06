package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.NumberRange;
import com.dani.spring.ecommerce_backend_api.validations.PaymentType;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;
import com.dani.spring.ecommerce_backend_api.validations.isCurrentOrHigherYear;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class PaymentMethodRequestDto {

    @Schema(description = "Tipo de tarjeta", example = "visa")
    @PaymentType
    @IsRequired
    private String type;

    @Schema(description = "Ultimos 4 digitos de la tarjeta", example = "1234")
    @NotNull
    @StringSize(min=4, max=4)
    private String last4;

    @Schema(description = "Mes de expiracion", example = "4")
    @NotNull
    @NumberRange(min=1, max=12)
    private Integer expiryMonth;

    @Schema(description = "Año de expiracion", example = "2032")
    @isCurrentOrHigherYear
    @NumberRange(max=2999)
    @NotNull
    private Integer expiryYear;

    @Schema(description = "Marcar el metodo como principal", example = "true")
    @NotNull
    private boolean isDefault;

    public PaymentMethodRequestDto(){
    }

    public PaymentMethodRequestDto(String type, String last4, Integer expiryMonth, Integer expiryYear, boolean isDefault) {
        this.type = type;
        this.last4 = last4;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.isDefault = isDefault;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
    

}
