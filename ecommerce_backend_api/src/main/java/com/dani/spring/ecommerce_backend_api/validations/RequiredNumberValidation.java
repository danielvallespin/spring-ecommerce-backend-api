package com.dani.spring.ecommerce_backend_api.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredNumberValidation implements ConstraintValidator<IsRequired, Number>{

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        return value != null;
    }

}
