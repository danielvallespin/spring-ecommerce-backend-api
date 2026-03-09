package com.dani.spring.ecommerce_backend_api.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }

}
