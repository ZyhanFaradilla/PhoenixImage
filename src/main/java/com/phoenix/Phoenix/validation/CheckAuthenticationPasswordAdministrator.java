package com.phoenix.Phoenix.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckAuthenticationPasswordAdministratorValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthenticationPasswordAdministrator {
    public String message();
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
