package com.company.edu.dto;

import com.company.edu.endpoint.validation.validator.AuthorExists;
import com.company.edu.endpoint.validation.validator.DictionaryCategoriesExist;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class BookInDto {
    private Long id;

    @NotBlank
    private String isbn;

    @NotBlank
    private String title;

    @NotNull
    @Min(1)
    private Integer numberOfPages;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @AuthorExists
    private Long authorId;

    @DictionaryCategoriesExist
    private Set<Long> dictionaryCategoryIds = new HashSet<>();
}
