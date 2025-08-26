package com.examly.springapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdminSecretValidator.class)
@Documented
public @interface ValidAdminSecret {
    String message() default "Invalid admin secret";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
