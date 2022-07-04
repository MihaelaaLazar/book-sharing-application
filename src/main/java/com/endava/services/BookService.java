package com.endava.services;

import com.endava.models.BookDto;
import com.endava.models.BooksRefDto;
import com.endava.models.UserDto;
import com.endava.repositories.BookRefRepo;
import com.endava.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


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
}
