package com.examly.springapp.validation;

import com.examly.springapp.dto.SignupRequest;
import com.examly.springapp.entity.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdminSecretValidator implements ConstraintValidator<ValidAdminSecret, SignupRequest> {

    private static final String SPECIAL_ADMIN_SECRET = "MySuperSecretAdmin123"; // 🔑 Change as needed

    @Override
    public boolean isValid(SignupRequest request, ConstraintValidatorContext context) {
        if (request.getRole() == Role.ADMIN) {
            return request.getAdminSecret() != null && request.getAdminSecret().equals(SPECIAL_ADMIN_SECRET);
        }
        return true; // Students do not require adminSecret
    }
}
