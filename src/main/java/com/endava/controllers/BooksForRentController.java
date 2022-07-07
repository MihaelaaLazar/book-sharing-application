package com.endava.controllers;


import com.endava.models.BooksForRentDto;
import com.endava.utils.RentalPeriod;
import com.endava.services.BooksForRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/booksForRent")
public class BooksForRentController {

    @Autowired
    private BooksForRentService booksForRentService;


    @RequestMapping(method= RequestMethod.GET)
    public List<BooksForRentDto> getBooksForRent() {
       return booksForRentService.getBooksForRent();
    }
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}/{bookRefId}/")
    public void rentBook(@PathVariable UUID userId, @PathVariable UUID bookRefId, @RequestBody RentalPeriod body) {
        booksForRentService.rentBook(userId, bookRefId, body);
    }


}
