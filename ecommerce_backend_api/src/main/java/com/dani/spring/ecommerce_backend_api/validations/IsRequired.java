package com.dani.spring.ecommerce_backend_api.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {
    RequiredStringValidation.class,
    RequiredNumberValidation.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IsRequired {
    String message() default "debe estar informado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
