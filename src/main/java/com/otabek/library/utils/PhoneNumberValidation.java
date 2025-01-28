package com.otabek.library.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidation implements ConstraintValidator<PhoneValidation, String> {

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone == null){
            return false;
        }
        return Pattern.matches("^\\+998(97|99|77|88|91|90|95|93|94|20|33|71)\\d{7}$", phone);
    }
}
