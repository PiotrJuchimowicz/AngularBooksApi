package com.company.edu.endpoint;

import com.company.edu.dto.*;
import com.company.edu.endpoint.validation.validator.BookExists;
import com.company.edu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@Validated
public class BookEndpoint {

    private BookService bookService;

    @Autowired
    public BookEndpoint(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void save(@RequestBody @Valid BookInDto bookInDto) {
        bookService.save(bookInDto);
    }

    @PostMapping(path = "/find")
    public GetBooksOutDto find(@RequestBody GetBooksInDto request) {
        return bookService.find(request);
    }

    @GetMapping("/{bookId}")
    public BookOutDto getOne(@BookExists @PathVariable("bookId") Long bookId) {
        return bookService.getOne(bookId);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@BookExists @PathVariable("bookId") Long bookId) {
        bookService.delete(bookId);
    }

    @PostMapping(path = "/borrow")
    public void borrow(@RequestBody @Valid BorrowRequestDto request) {
        bookService.borrow(request);
    }
}
