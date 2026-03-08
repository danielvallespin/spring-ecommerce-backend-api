package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public class OrderResponseDto {

    @Schema(description = "Id del pedido", example = "6")
    private final Long orderId;

    @Schema(description = "Id del usuario", example = "8")
    private final Long userId;

    @Schema(description = "Precio total del pedido", example = "356.74")
    private final BigDecimal orderAmount;

    @Schema(description = "Estado del pedido", example = "paid")
    private final String status;

    @Schema(description = "Fecha del pedido", example = "18/03/2025")
    private final LocalDateTime createdAt;

    public OrderResponseDto(Long orderId, Long userId, BigDecimal orderAmount, String status, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderAmount = orderAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    

}
