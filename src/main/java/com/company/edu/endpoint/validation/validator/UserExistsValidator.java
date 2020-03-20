package com.company.edu.endpoint.validation.validator;

import com.company.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserExistsValidator implements ConstraintValidator<UserExists, Long> {

    private UserService service;

    @Autowired
    public UserExistsValidator(UserService service) {
        this.service = service;
    }

    @Override
    public void initialize(UserExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext) {
        if (userId == null) {
            return true;
        }

        return service.doesUserExist(userId);
    }
}
