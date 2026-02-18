package com.dani.spring.ecommerce_backend_api.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringSizeValidation 
        implements ConstraintValidator<StringSize, String> {

    private int min;
    private int max;

    @Override
    public void initialize(StringSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //Evitamos que pete si es null, no se controla aqui ya que no es esta su validacion
        if (value == null) return true;

        Integer length = value.length();
        return length >= min && length <= max;
    }
}