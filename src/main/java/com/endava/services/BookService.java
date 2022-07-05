package com.endava.services;

import com.endava.models.BookDto;
import com.endava.models.BooksRefDto;
import com.endava.models.UserDto;
import com.endava.repositories.BookRefRepo;
import com.endava.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void createBook(UUID userId, BookDto book, UserDto userDto) {
        bookRepo.save(book);
        bookRefRepo.save(new BooksRefDto(null, userDto.getUserId(), book.getBookId()));
    }

    public Stream<List<BookDto>> getBooksByUserId(UUID userId) {
        List<BooksRefDto> booksRefDto = bookRefRepo.findByUserUserId(userId);
        return booksRefDto.stream()
                .map(bookRefDto -> bookRepo.findByBookId(bookRefDto.getBook().getBookId()));

    }

    public List<BookDto> getBooksByTitleOrAuthor(Optional<String> title, Optional<String> author) {
        return bookRepo.findByTitleOrAuthor(title, author);
    }
}
