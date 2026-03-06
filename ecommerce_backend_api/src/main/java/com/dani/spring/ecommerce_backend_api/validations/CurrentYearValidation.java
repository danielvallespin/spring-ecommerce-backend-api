package com.dani.spring.ecommerce_backend_api.validations;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrentYearValidation implements ConstraintValidator<isCurrentOrHigherYear, Integer>{

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return true;

        return value >= LocalDate.now().getYear();
    }

}
