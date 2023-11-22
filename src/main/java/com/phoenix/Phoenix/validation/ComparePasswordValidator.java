package com.phoenix.Phoenix.validation;

import com.phoenix.Phoenix.utility.MapperHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComparePasswordValidator implements ConstraintValidator<ComparePassword, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var password = MapperHelper.getStringField(value, "password");
        var passwordConfirm = MapperHelper.getStringField(value, "confirmPassword");
        return password.equals(passwordConfirm);
    }
}
