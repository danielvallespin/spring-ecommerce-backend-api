package com.dani.spring.ecommerce_backend_api.dto.responses;

public class PaymentMethodResponseDto {

    private Long paymentId;

    private Long userId;

    private String type;

    private String last4;

    private Integer expiryMonth;

    private Integer expiryYear;

    private boolean isDefault;

    private boolean enabled;

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

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
