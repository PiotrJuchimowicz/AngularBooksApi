package com.company.edu.service;

import com.company.edu.dto.DictionaryCategoryDto;

import java.util.List;

public interface DictionaryCategoryService {
    List<DictionaryCategoryDto> getDictionaryCategories();

    boolean doesDictionaryCategoryExist(Long dictionaryId);
}
