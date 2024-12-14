package week2New.week2New.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenValidator implements ConstraintValidator<Even, Integer> {
    @Override
    public boolean isValid(Integer inputNo, ConstraintValidatorContext constraintValidatorContext) {
        return inputNo%2==0;
    }
}
