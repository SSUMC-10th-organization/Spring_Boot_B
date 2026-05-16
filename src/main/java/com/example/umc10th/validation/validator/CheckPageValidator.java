package com.example.umc10th.validation.validator;

import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.validation.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; 
        }
        boolean isValid = value >= 0;
        
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_UNDER_ZERO.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
