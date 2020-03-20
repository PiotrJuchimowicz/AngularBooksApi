package com.company.edu.service;

import com.company.edu.dto.PersonDto;

import java.util.List;

public interface AuthorService {

    List<PersonDto> getAuthors();

    boolean doesAuthorExist(Long authorId);
}
