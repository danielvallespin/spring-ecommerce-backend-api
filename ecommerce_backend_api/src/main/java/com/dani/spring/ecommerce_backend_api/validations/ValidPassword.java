package com.dani.spring.ecommerce_backend_api.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {
    ValidPasswordValidation.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValidPassword {

    String message() default "La contraseña debe tener al menos 8 caracteres e incluir al menos una letra mayúscula, una letra minúscula y un número.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
