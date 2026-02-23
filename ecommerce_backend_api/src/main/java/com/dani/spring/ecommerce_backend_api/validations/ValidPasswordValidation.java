package com.dani.spring.ecommerce_backend_api.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPasswordValidation implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }

}
