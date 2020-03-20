package com.company.edu.endpoint.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthorExistsValidator.class)
public @interface AuthorExists {
    String message() default "Author with id = ${validatedValue} does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
