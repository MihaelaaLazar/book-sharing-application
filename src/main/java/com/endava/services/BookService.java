package com.endava.services;

import com.endava.models.BookDto;
import com.endava.models.BooksForRentDto;
import com.endava.models.BooksRefDto;
import com.endava.models.RentedBooksDto;
import com.endava.repositories.BookRefRepo;
import com.endava.repositories.BookRepo;
import com.endava.repositories.BooksForRentRepo;
import com.endava.repositories.RentedBooksRepo;
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

    public ResponseEntity<?> createBook(UUID userId, BookDto book) {
        bookRepo.save(book);
        BooksRefDto booksRefDto = new BooksRefDto(null, userId, book.getBookId());
        bookRefRepo.save(booksRefDto);
        booksForRentRepo.save(new BooksForRentDto(null, booksRefDto));
        return new ResponseEntity<>("Book created." ,  HttpStatus.CREATED);

    }

    public Stream<Object> getBooksByUserId(UUID userId) {
        List<BooksRefDto> booksRefDto = bookRefRepo.findByUserUserId(userId);
        return booksRefDto.stream()
                .map(bookRefDto -> bookRepo.findByBookId(bookRefDto.getBook().getBookId()));
    }
    public List<?> getBooksByTitleOrAuthor(Optional<String> title, Optional<String> author) {
        List<BookDto> books = bookRepo.findByTitleOrAuthor(title, author);
        for(BookDto book : books){
            List<RentedBooksDto> rentedBooks = rentedBooksRepo.findBookByBookId(book.getBookId());
            for(RentedBooksDto rentedBook : rentedBooks){
                if(book.getBookId().equals(rentedBook.getBooksRefDto().getBook().getBookId())){
                    return List.of(rentedBook);
                }
            }
        }
        return books;
    }
}
