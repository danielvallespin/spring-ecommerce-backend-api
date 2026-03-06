package com.dani.spring.ecommerce_backend_api.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PaymentTypeValidation implements ConstraintValidator<PaymentType, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return "visa".equals(value) || "mastercard".equals(value);
    }

}
