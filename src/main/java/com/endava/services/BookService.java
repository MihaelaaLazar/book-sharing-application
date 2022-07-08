package com.endava.services;

import com.endava.models.BookDto;
import com.endava.models.BooksForRentDto;
import com.endava.models.BooksRefDto;
import com.endava.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookRefRepo bookRefRepo;

    @Autowired
    private BooksForRentRepo booksForRentRepo;

    @Autowired
    private RentedBooksRepo rentedBooksRepo;


    public List<BookDto> getAllBooks() {
        return bookRepo.findAll();
    }

    public ResponseEntity<?> createBook(UUID userId, BookDto book) {
        bookRepo.save(book);
        BooksRefDto booksRefDto = new BooksRefDto(null, userId, book.getBookId());
        bookRefRepo.save(booksRefDto);
        booksForRentRepo.save(new BooksForRentDto(null, booksRefDto));
        return new ResponseEntity<>("Book created.", HttpStatus.CREATED);

    }

    public Stream<Object> getBooksByUserId(UUID userId) {
        List<BooksRefDto> booksRefDto = bookRefRepo.findByUserUserId(userId);
        return booksRefDto.stream()
                .map(bookRefDto -> bookRepo.findByBookId(bookRefDto.getBook().getBookId()));
    }

    public Stream<?> getBooksByTitleOrAuthor(Optional<String> title, Optional<String> author) {
        List<BookDto> books = bookRepo.findByTitleOrAuthor(title, author);
        Stream<?> availableBooks = books.stream()
                .filter(book -> booksForRentRepo.findByBookId(book.getBookId()) != null);

        Stream<?> rentedBooks = books.stream()
                .filter(book -> rentedBooksRepo.findOneBookByBookId(book.getBookId()) != null)
                .map(book -> rentedBooksRepo.findOneBookByBookId(book.getBookId()))
                .filter(rentedBook -> rentedBook.getReturningDate() != null);

        return Stream.concat(availableBooks, rentedBooks);

    }
}
