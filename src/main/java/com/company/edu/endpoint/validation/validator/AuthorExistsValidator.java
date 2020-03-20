package com.company.edu.endpoint.validation.validator;

import com.company.edu.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AuthorExistsValidator implements ConstraintValidator<AuthorExists, Long> {
    private AuthorService service;

    @Autowired
    public AuthorExistsValidator(AuthorService service) {
        this.service = service;
    }

    @Override
    public void initialize(AuthorExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long authorId, ConstraintValidatorContext constraintValidatorContext) {
        if (authorId == null) {
            return true;
        }

        return service.doesAuthorExist(authorId);
    }
}
