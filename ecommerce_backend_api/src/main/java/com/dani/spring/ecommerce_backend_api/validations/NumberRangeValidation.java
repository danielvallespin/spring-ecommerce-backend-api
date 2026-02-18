package com.dani.spring.ecommerce_backend_api.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumberRangeValidation implements ConstraintValidator<NumberRange, Number> {

    private long min;
    private long max;

    @Override
    public void initialize(NumberRange annotation) {
        this.min = annotation.min();
        this.max = annotation.max();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        //Evitamos que pete si es null, no se controla aqui ya que no es esta su validacion
        if (value == null) return true;

        long longValue = value.longValue();
        return longValue >= min && longValue <= max;
    }
}