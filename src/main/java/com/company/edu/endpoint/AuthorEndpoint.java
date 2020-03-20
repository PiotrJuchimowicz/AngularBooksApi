package com.company.edu.endpoint;

import com.company.edu.dto.PersonDto;
import com.company.edu.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorEndpoint {

    private AuthorService authorService;

    @Autowired
    public AuthorEndpoint(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<PersonDto> getAuthors() {
        return authorService.getAuthors();
    }
}
