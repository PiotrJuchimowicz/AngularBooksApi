package com.company.edu.endpoint.validation.validator;

import com.company.edu.service.BookService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class BookExistsValidator implements ConstraintValidator<BookExists, Long> {

    private BookService service;

    public BookExistsValidator(BookService service) {
        this.service = service;
    }

    @Override
    public void initialize(BookExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long bookId, ConstraintValidatorContext constraintValidatorContext) {
        if (bookId == null) {
            return true;
        }

        return service.doesBookExist(bookId);
    }
}
