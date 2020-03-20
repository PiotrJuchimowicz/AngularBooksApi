package com.company.edu.service;

import com.company.edu.dto.PersonDto;
import com.company.edu.model.Person;
import com.company.edu.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> getAuthors() {
        return authorRepository.findAll().stream().map(Person::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean doesAuthorExist(Long authorId) {
        return authorRepository.existsById(authorId);
    }
}
