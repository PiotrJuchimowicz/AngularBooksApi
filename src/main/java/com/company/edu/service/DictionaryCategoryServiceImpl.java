package com.company.edu.service;

import com.company.edu.dto.DictionaryCategoryDto;
import com.company.edu.model.DictionaryCategory;
import com.company.edu.repository.DictionaryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryCategoryServiceImpl implements DictionaryCategoryService {

    private DictionaryCategoryRepository dictionaryCategoryRepository;

    @Autowired
    public DictionaryCategoryServiceImpl(DictionaryCategoryRepository dictionaryCategoryRepository) {
        this.dictionaryCategoryRepository = dictionaryCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DictionaryCategoryDto> getDictionaryCategories() {
        return dictionaryCategoryRepository.findAll().stream().map(DictionaryCategory::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean doesDictionaryCategoryExist(Long dictionaryId) {
        return dictionaryCategoryRepository.existsById(dictionaryId);
    }
}
