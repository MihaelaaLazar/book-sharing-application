package com.endava.controllers;

import com.endava.models.BookDto;
import com.endava.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/books")
@Tag(name = "2. Book", description = "Book API")
@CrossOrigin(origins = "http://localhost:3000")
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
            description = "Creates a book with user's id"
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/{userId}/create",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createBookWithUserId(
            @PathVariable UUID userId,
            @RequestPart("body")  BookDto book,
            @RequestPart("file")  MultipartFile file
    ) {
        return bookService.createBook(userId, book, file);
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
            description = "Finds book by title or author")
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/search")
    public Stream<?> getBooksByTitleOrAuthor(
            @RequestParam("query") String query)
    {
        return bookService.getBooksByTitleOrAuthor(query);
    }
    @Operation(summary = "Get book by title or author with pagination",
            description = "Gets book by title or author with pagination")
    @RequestMapping(method = RequestMethod.GET, value = "/search/{page}/{pageSize}")
    public ResponseEntity<?> getBooksByTitleOrAuthorWithPagination(
            @RequestParam("query") String query,
            @PathVariable int page,
            @PathVariable int pageSize)
    {
        return bookService.getBookByTitleOrAuthorWithPagination(query, page, pageSize);
    }

    @Operation(
            summary = "Update book",
            description = "Updates book")
    @RequestMapping(method = RequestMethod.PUT, value = "/{bookId}/update")
    public ResponseEntity<?> updateBook(@PathVariable UUID bookId, @RequestBody BookDto book) {
        return bookService.updateBook(bookId, book);
    }

    @Operation(
            summary = "Get all books with pagination",
            description = "Gets all books with pagination"
    )
    @RequestMapping(method = RequestMethod.GET, value = "/{page}/{pageSize}")
    public ResponseEntity<?> getBooksWithPagination(@PathVariable int page, @PathVariable int pageSize) {
        return bookService.getBooksWithPagination(page, pageSize);
    }

    @Operation(
            summary = "Get all books by userId with pagination",
            description = "Gets all books by userId with pagination"
    )
    @RequestMapping(method = RequestMethod.GET, value="/{userId}/{page}/{pageSize}")
    public ResponseEntity<?> getBooksWithUserIdAndPagination(@PathVariable UUID userId, @PathVariable int page, @PathVariable int pageSize){
        return bookService.getBooksWithUserIdAndPagination(userId, page, pageSize);
    }

    @Operation(
            summary = " Get book by bookId",
            description = "Gets book by bookId"
    )
    @RequestMapping(method = RequestMethod.GET, value = "/bookData/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable UUID bookId) {
        return bookService.getBookByBookId(bookId);
    }
}
