package com.company.edu.service;

import com.company.edu.dto.*;


public interface BookService {

    GetBooksOutDto find(GetBooksInDto getBooksInDto);

    void delete(Long bookId);

    void borrow(BorrowRequestDto request);

    BookOutDto getOne(Long bookId);

    void save(BookInDto bookInDto);

    boolean doesBookExist(Long bookId);
}
