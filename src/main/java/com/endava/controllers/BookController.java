package com.endava.controllers;

import com.endava.models.BookDto;
import com.endava.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/books")
@Tag(name = "2. Book", description = "Book API")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(
            summary = "Find all books",
            description = "Finds all books")
    @RequestMapping(
            method = RequestMethod.GET)
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(
            summary = "As a user I can create a book",
            description = "Creates a book with user's id")
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/{userId}/create")
    public ResponseEntity<?> createBookWithUserId(@PathVariable UUID userId, @RequestBody BookDto book) {
        return bookService.createBook(userId, book);
    }

    @Operation(
            summary = "Find books by user id",
            description = "Finds books by user id")
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{userId}")
    public Stream<Object> getBooksByUserId(@PathVariable UUID userId) {
        return bookService.getBooksByUserId(userId);
    }

    @Operation(
            summary = "Find book by title or author",
            description = "Finds book by title or author",
            parameters = {
                    @Parameter(name = "title", description = "Book's title"),
                    @Parameter(name = "author", description = "Book's author")
            })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/search")
    public Stream<?> getBooksByTitleOrAuthor(
            @RequestParam("title") Optional<String> title,
            @RequestParam("author") Optional<String> author) {
        return bookService.getBooksByTitleOrAuthor(title, author);
    }

    @Operation(
            summary = "Update book",
            description = "Updates book")
    @RequestMapping(method = RequestMethod.PUT, value = "/{bookId}/update")
    public ResponseEntity<?> updateBook(@PathVariable UUID bookId, @RequestBody BookDto book) {
        return bookService.updateBook(bookId, book);
    }

}
