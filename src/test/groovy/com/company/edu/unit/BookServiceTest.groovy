package com.company.edu.unit

import com.company.edu.dto.BookInDto
import com.company.edu.dto.BookOutDto
import com.company.edu.dto.BorrowRequestDto
import com.company.edu.model.Author
import com.company.edu.model.Book
import com.company.edu.model.User
import com.company.edu.repository.AuthorRepository
import com.company.edu.repository.BookRepository
import com.company.edu.repository.DictionaryCategoryRepository
import com.company.edu.repository.UserRepository
import com.company.edu.service.BookService
import com.company.edu.service.BookServiceImpl
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

class BookServiceTest extends Specification {

    private BookService bookService
    private Long bookId = 1
    private Long borrowerId = 1

    def setup() {
        BookRepository bookRepositoryMock = Mock(BookRepository)
        bookRepositoryMock.getOne(bookId) >> getTestBook(bookId)

        UserRepository userRepositoryMock = Mock(UserRepository)
        userRepositoryMock.getOne(borrowerId) >> getTestUser(borrowerId)

        bookService = new BookServiceImpl(bookRepositoryMock, userRepositoryMock, Mock(AuthorRepository), Mock(DictionaryCategoryRepository))
    }


    def "borrow book"() {
        given:
        BorrowRequestDto borrowRequestDto = new BorrowRequestDto(borrowerId, bookId)
        when:
        bookService.borrow(borrowRequestDto)
        then:
        BookOutDto bookOutDto = bookService.getOne(bookId)
        bookOutDto.getBorrower().getId() == borrowerId
    }

    def "return book after borrow"() {
        given:
        bookService.borrow(new BorrowRequestDto(borrowerId, bookId))
        BorrowRequestDto borrowRequestDto = new BorrowRequestDto(null, bookId)
        when:
        bookService.borrow(borrowRequestDto)
        then:
        BookOutDto bookOutDto = bookService.getOne(bookId)
        bookOutDto.getBorrower() == null
    }

    def "edit book"() {
        given:
        String title = "newTitle"
        String isbn = "new Isbn"
        Integer numberOfPages = 100
        LocalDate releaseDate = LocalDate.of(1996, Month.MARCH, 28)
        BookInDto bookInDto = new BookInDto(bookId, isbn, title, numberOfPages, releaseDate, null, new HashSet<Long>())
        when:
        bookService.save(bookInDto)
        then:
        BookOutDto bookOutDto = bookService.getOne(bookId)
        bookOutDto.getIsbn() == isbn
        bookOutDto.getTitle() == title
        bookOutDto.getNumberOfPages() == numberOfPages
        bookOutDto.getReleaseDate() == releaseDate
    }


    private Book getTestBook(Long bookId) {
        Book book = new Book()
        book.setId(bookId)
        book.setAuthor(new Author())
        book.setDictionaryCategories(new HashSet<Dictionary>())
        book.setIsbn("1234")
        book.setNumberOfPages(10)
        book.setReleaseDate(LocalDate.now())
        book.setTitle("title")
        return book
    }

    private User getTestUser(Long userId) {
        User user = new User()
        user.setId(userId)
        return user
    }
}
