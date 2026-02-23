package com.dani.spring.ecommerce_backend_api.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
