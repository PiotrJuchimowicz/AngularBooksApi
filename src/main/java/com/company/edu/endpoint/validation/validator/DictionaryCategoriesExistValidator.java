package com.company.edu.endpoint.validation.validator;

import com.company.edu.service.DictionaryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DictionaryCategoriesExistValidator implements ConstraintValidator<DictionaryCategoriesExist, Set<Long>> {

    private DictionaryCategoryService service;

    @Autowired
    public DictionaryCategoriesExistValidator(DictionaryCategoryService service) {
        this.service = service;
    }

    @Override
    public void initialize(DictionaryCategoriesExist constraintAnnotation) {

    }

    @Override
    public boolean isValid(Set<Long> dictionaryIds, ConstraintValidatorContext constraintValidatorContext) {
        if (dictionaryIds == null || dictionaryIds.isEmpty()) {
            return true;
        }

        List<Long> notExistingIds = dictionaryIds.stream().filter(dictionaryId -> !service.doesDictionaryCategoryExist(dictionaryId)).collect(Collectors.toList());

        return notExistingIds.isEmpty();
    }
}
