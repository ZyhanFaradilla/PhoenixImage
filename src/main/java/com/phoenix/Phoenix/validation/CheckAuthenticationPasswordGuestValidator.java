package com.phoenix.Phoenix.validation;

import com.phoenix.Phoenix.dto.guest.ChangePasswordGuestDTO;
import com.phoenix.Phoenix.service.GuestService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckAuthenticationPasswordGuestValidator implements ConstraintValidator<CheckAuthenticationPasswordGuest, ChangePasswordGuestDTO> {
    @Autowired
    private GuestService guestService;

    @Override
    public boolean isValid(ChangePasswordGuestDTO value, ConstraintValidatorContext context) {
        return guestService.checkUsernamePasswordGuest(value.getUsername(), value.getOldPassword());
    }
}
