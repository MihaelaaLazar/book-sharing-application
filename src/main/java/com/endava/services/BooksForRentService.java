package com.endava.services;

import com.endava.models.BooksForRentDto;
import com.endava.utils.RentalPeriod;
import com.endava.models.RentedBooksDto;
import com.endava.repositories.BooksForRentRepo;
import com.endava.repositories.RentedBooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;


@Service
public class BooksForRentService {

    @Autowired
    private BooksForRentRepo booksForRentRepo;

    @Autowired
    private RentedBooksRepo rentedBooksRepo;


    public List<BooksForRentDto> getBooksForRent(){
        return booksForRentRepo.findAll();
    }

    public ResponseEntity<?> rentBook(UUID userId, UUID bookRefId, RentalPeriod body) {
        BooksForRentDto bookAvailable = booksForRentRepo.findByBookRefId(bookRefId).orElse(null);
        if (bookAvailable == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Book not available");
        }
        if (bookAvailable.getBookRef().getUser().getUserId().equals(userId)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("You can't rent your own book");
        } else {
            LocalDate date = LocalDate.now().plus(body.getPeriod());
            long remainingDays = Period.between(LocalDate.now(), date).getDays();
            RentedBooksDto rentedBook = new RentedBooksDto(null, userId, bookRefId, remainingDays);
            rentedBooksRepo.save(rentedBook);
            booksForRentRepo.delete(bookAvailable);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Book rented");
        }
    }
}
