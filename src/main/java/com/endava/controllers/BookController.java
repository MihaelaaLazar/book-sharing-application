package com.endava.controllers;

import com.endava.models.BookDto;
import com.endava.models.UserDto;
import com.endava.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}/create")
    public void createBookWithUserId(@PathVariable UUID userId, @RequestBody BookDto book, UserDto userDto) {

        bookService.createBook(userId, book, userDto);
    }

}
