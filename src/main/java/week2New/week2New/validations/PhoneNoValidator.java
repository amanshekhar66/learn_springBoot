package week2New.week2New.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNoValidator implements ConstraintValidator<PhoneNoValidation,String> {

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext constraintValidatorContext) {
        if(phoneNo == null)
            return false;
        else
            return phoneNo.matches("^[6-9]\\d{9}$");
    }
}
