package week2New.week2New.validations;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface Even {
    String message() default "The provided value should be even";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
