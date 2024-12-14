package week2New.week2New.validations;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface PhoneNoValidation {
    String message() default "The phone no has to be valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
