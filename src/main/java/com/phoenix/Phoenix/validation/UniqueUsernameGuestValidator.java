package com.phoenix.Phoenix.validation;

import com.phoenix.Phoenix.service.GuestService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameGuestValidator implements ConstraintValidator<UniqueUsernameGuest, String> {
    @Autowired
    private GuestService guestService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !guestService.isUsernameGuestExist(value);
    }
}
