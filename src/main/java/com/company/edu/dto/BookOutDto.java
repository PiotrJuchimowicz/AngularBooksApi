package com.company.edu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class BookOutDto {

    private Long id;

    private String isbn;

    private String title;

    private Integer numberOfPages;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    private PersonDto author;

    private PersonDto borrower;

    private Set<DictionaryCategoryDto> dictionaryCategories = new HashSet<>();
}
