package com.phoenix.Phoenix.validation;

import com.phoenix.Phoenix.dto.account.ChangePasswordAdministratorDTO;
import com.phoenix.Phoenix.service.GuestService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckAuthenticationPasswordAdministratorValidator implements ConstraintValidator<CheckAuthenticationPasswordAdministrator, ChangePasswordAdministratorDTO> {
    @Autowired
    private GuestService guestService;

    @Override
    public boolean isValid(ChangePasswordAdministratorDTO value, ConstraintValidatorContext context) {
        return guestService.checkUsernamePasswordAdministrator(value.getUsername(), value.getOldPassword());
    }
}
