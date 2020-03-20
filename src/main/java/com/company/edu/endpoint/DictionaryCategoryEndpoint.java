package com.company.edu.endpoint;

import com.company.edu.dto.DictionaryCategoryDto;
import com.company.edu.service.DictionaryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dictionary-categories")
public class DictionaryCategoryEndpoint {

    private DictionaryCategoryService dictionaryCategoryService;

    @Autowired
    public DictionaryCategoryEndpoint(DictionaryCategoryService dictionaryCategoryService) {
        this.dictionaryCategoryService = dictionaryCategoryService;
    }

    @GetMapping
    public List<DictionaryCategoryDto> getDictionaryCategories() {
        return dictionaryCategoryService.getDictionaryCategories();
    }
}
