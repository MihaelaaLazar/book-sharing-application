package com.endava.controllers;

import com.endava.models.BookDto;
import com.endava.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}/create")
    public ResponseEntity<?> createBookWithUserId(@PathVariable UUID userId, @RequestBody BookDto book) {
        return bookService.createBook(userId, book);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public Stream<Object> getBooksByUserId(@PathVariable UUID userId) {
        return bookService.getBooksByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public List<BookDto> getBooksByTitleOrAuthor(@RequestParam("title") Optional<String> title, @RequestParam("author") Optional<String> author) {
        return bookService.getBooksByTitleOrAuthor(title, author);
    }

}
