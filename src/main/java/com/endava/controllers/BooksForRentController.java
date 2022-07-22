package com.endava.controllers;


import com.endava.models.BooksForRentDto;
import com.endava.utils.RentalPeriod;
import com.endava.services.BooksForRentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/booksForRent")
@Tag(name = "3. BooksForRent", description = "BooksForRent API")
@CrossOrigin(origins = "http://localhost:3000")
public class BooksForRentController {

    @Autowired
    private BooksForRentService booksForRentService;

    @Operation(
            summary = "Find all books available for rent",
            description = "Finds all books available for rent")
    @RequestMapping(
            method = RequestMethod.GET)
    public List<BooksForRentDto> getBooksForRent() {
        return booksForRentService.getBooksForRent();
    }

    @Operation(
            summary = "As a user I can rent a book with my id and the book id of the book I want to rent",
            description = "Rents a book with the given id")
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/{userId}/{bookRefId}/")
    public void rentBook(@PathVariable UUID userId, @PathVariable UUID bookRefId, @RequestBody RentalPeriod body) {
        booksForRentService.rentBook(userId, bookRefId, body);
    }

    @Operation(
            summary = "Get all available books with pagination",
            description = "Get all available books with pagination")
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{page}/{pageSize}")
    public ResponseEntity<?> getAvailableBookWithPagination(@PathVariable int page, @PathVariable int pageSize) {
        return booksForRentService.getAvailableBookWithPagination(page, pageSize);
    }


}
