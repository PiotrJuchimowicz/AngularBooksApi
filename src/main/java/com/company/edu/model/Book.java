package com.company.edu.model;

import com.company.edu.dto.BookOutDto;
import com.company.edu.dto.DictionaryCategoryDto;
import com.company.edu.dto.PersonDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Book extends AbstractEntity {

    private String isbn;

    private String title;

    @Column(nullable = false)
    private Integer numberOfPages;

    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @ManyToMany
    @JoinTable(
            name = "book_dictionary_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "dictionary_id")
    )
    private Set<DictionaryCategory> dictionaryCategories = new HashSet<>();

    @Formula("SELECT count(*) FROM BOOK_DICTIONARY_CATEGORY c WHERE c.BOOK_ID = id")
    private Long dictionaryCategoriesCount;

    public Book() {
    }

    public BookOutDto toDto() {

        PersonDto authorDto = null;
        if(this.author != null) {
             authorDto = this.author.toDto();
        }

        PersonDto borrowerDto = null;
        if (this.borrower != null) {
            borrowerDto = this.borrower.toDto();
        }

        Set<DictionaryCategoryDto> dictionariesDtos = this.dictionaryCategories.stream().map(DictionaryCategory::toDto).collect(Collectors.toSet());
        return new BookOutDto(this.getId(), this.isbn, this.title, this.numberOfPages, this.releaseDate, authorDto, borrowerDto, dictionariesDtos);
    }
}
