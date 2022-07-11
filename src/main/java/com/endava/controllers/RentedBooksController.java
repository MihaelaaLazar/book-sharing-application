package com.endava.controllers;

import com.endava.utils.DefaultPeriodsForExtendedTime;
import com.endava.services.RentedBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/rentedBooks")
@Tag(name = "4. RentedBooks", description = "RentedBooks API")
public class RentedBooksController {

    @Autowired
    private RentedBooksService rentedBooksService;


    @Operation(
            summary = "Find all book given to rent by user",
            description = "Finds all book given to rent by user"
    )
    @RequestMapping(
            value = "/{userId}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentedBooks(@PathVariable("userId") UUID userId) {
        return rentedBooksService.getRentedBooks(userId);
    }

    @Operation(
            summary = "As a user I want to extend the time of a book which I have borrowed",
            description = "Extends the time of a book"
    )
    @RequestMapping(
            value = "/{rentedBookId}",
            method = RequestMethod.PUT,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> extendTimeForBook(@PathVariable("rentedBookId") UUID rentedBookId, @RequestBody DefaultPeriodsForExtendedTime body) {
        return rentedBooksService.extendTimeForBook(rentedBookId, body);
    }

    @Operation(
            summary = "Find all books which I currently borrowed",
            description = "Finds all books which I currently borrowed"
    )
    @RequestMapping(
            value = "/by/{userId}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentedBookByUserId(@PathVariable("userId") UUID userId) {
        return rentedBooksService.getRentedBookByUserId(userId);
    }
}
