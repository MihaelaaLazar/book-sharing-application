package com.endava.controllers;

import com.endava.utils.DefaultPeriodsForExtendedTime;
import com.endava.services.RentedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/rentedBooks")
public class RentedBooksController {

    @Autowired
    private RentedBooksService rentedBooksService;

    @RequestMapping(
            value = "/{userId}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentedBooks(@PathVariable("userId") UUID userId) {
        return rentedBooksService.getRentedBooks(userId);
    }

    @RequestMapping(
            value = "/{rentedBookId}",
            method = RequestMethod.PUT,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> extendTimeForBook(@PathVariable("rentedBookId") UUID rentedBookId, @RequestBody DefaultPeriodsForExtendedTime body) {
        return rentedBooksService.extendTimeForBook(rentedBookId, body);
    }

    @RequestMapping(
            value = "/by/{userId}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentedBookByUserId(@PathVariable("userId") UUID userId) {
        return rentedBooksService.getRentedBookByUserId(userId);
    }
}
