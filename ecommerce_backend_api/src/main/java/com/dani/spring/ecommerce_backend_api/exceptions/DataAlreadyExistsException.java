package com.dani.spring.ecommerce_backend_api.exceptions;

public class DataAlreadyExistsException extends RuntimeException {

    public DataAlreadyExistsException(String message) {
        super(message);
    }

}
