package com.company.edu.service;

import com.company.edu.dto.*;
import com.company.edu.model.Author;
import com.company.edu.model.Book;
import com.company.edu.model.DictionaryCategory;
import com.company.edu.model.User;
import com.company.edu.repository.AuthorRepository;
import com.company.edu.repository.BookRepository;
import com.company.edu.repository.DictionaryCategoryRepository;
import com.company.edu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private AuthorRepository authorRepository;
    private DictionaryCategoryRepository dictionaryCategoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, AuthorRepository authorRepository, DictionaryCategoryRepository dictionaryCategoryRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.dictionaryCategoryRepository = dictionaryCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public GetBooksOutDto find(GetBooksInDto getBooksInDto) {
        long totalCount = bookRepository.count();
        validatePagination(getBooksInDto.getPagination(), totalCount);
        List<BookOutDto> bookOutDtos = bookRepository.findAll(
                PageRequest.of(
                        getBooksInDto.getPagination().getPageNumber(), getBooksInDto.getPagination().getPageSize(),
                        Sort.by(Sort.Direction.fromString(getBooksInDto.getSorting().getOrder().toString()), getBooksInDto.getSorting().getSortColumns())))
                .stream().map(Book::toDto).collect(Collectors.toList());

        return new GetBooksOutDto(bookOutDtos, totalCount);
    }

    @Override
    @Transactional
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public void borrow(BorrowRequestDto request) {
        Book book = bookRepository.getOne(request.getBookId());

        if (request.getBorrowerId() == null) {
            book.setBorrower(null);
        } else {
            User borrower = userRepository.getOne(request.getBorrowerId());
            book.setBorrower(borrower);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BookOutDto getOne(Long bookId) {
        return bookRepository.getOne(bookId).toDto();
    }

    @Override
    @Transactional
    public void save(BookInDto bookInDto) {
        Book book;

        if (bookInDto.getId() == null) {
            book = new Book();
        } else {
            book = bookRepository.getOne(bookInDto.getId());
        }

        setBookProperties(book, bookInDto);
        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean doesBookExist(Long bookId) {
        return bookRepository.existsById(bookId);
    }

    private void validatePagination(GetBooksInDto.SimplePagination simplePagination, long totalCount) {
        int pageNumber = simplePagination.getPageNumber();
        int pageSize = simplePagination.getPageSize();

        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
            simplePagination.setPageSize(pageSize);
        }

        int maxPage = ((int) Math.ceil((double) totalCount / pageSize)) - 1;

        if(maxPage < 0) {
            maxPage = 0;
        }

        if (pageNumber < 0) {
            simplePagination.setPageNumber(0);
        } else if (pageNumber > maxPage) {
            simplePagination.setPageNumber(maxPage);
        }
    }

    private void setBookProperties(Book book, BookInDto dto) {
        if (dto.getIsbn() != null && !dto.getIsbn().isEmpty()) {
            book.setIsbn(dto.getIsbn());
        }

        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            book.setTitle(dto.getTitle());
        }

        if (dto.getNumberOfPages() != null) {
            book.setNumberOfPages(dto.getNumberOfPages());
        }

        if (dto.getReleaseDate() != null) {
            book.setReleaseDate(dto.getReleaseDate());
        }

        if (dto.getAuthorId() != null) {
            Author author = authorRepository.getOne(dto.getAuthorId());
            book.setAuthor(author);
        }

        if (dto.getDictionaryCategoryIds() != null) {
            Set<DictionaryCategory> dictionaries = dto.getDictionaryCategoryIds().stream().map(dictionaryId -> dictionaryCategoryRepository.getOne(dictionaryId)).collect(Collectors.toSet());
            book.setDictionaryCategories(dictionaries);
        }
    }
}
