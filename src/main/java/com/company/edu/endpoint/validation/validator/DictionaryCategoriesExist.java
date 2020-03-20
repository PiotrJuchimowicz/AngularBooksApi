package com.company.edu.endpoint.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DictionaryCategoriesExistValidator.class)
public @interface DictionaryCategoriesExist {
    String message() default "Dictionaries does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
