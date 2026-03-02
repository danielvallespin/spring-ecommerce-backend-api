package com.dani.spring.ecommerce_backend_api.exceptions;

public class InsufficientStockException extends RuntimeException {

    private final int availableStock;

    public InsufficientStockException(String message, Integer availableStock) {
        super(message);
        this.availableStock = availableStock;
    }

    public int getAvailableStock() {
        return availableStock;
    }

}
