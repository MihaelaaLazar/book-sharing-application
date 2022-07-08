package com.endava.services;


import com.endava.utils.DefaultPeriodsForExtendedTime;
import com.endava.models.RentedBooksDto;
import com.endava.repositories.RentedBooksRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RentedBooksService {

    @Autowired
    private RentedBooksRepo rentedBooksRepo;

    public ResponseEntity<?> getRentedBooks(UUID userId) {
        List<JSONObject> jsonObject = new ArrayList<>();
        List<RentedBooksDto> rentedBooks = rentedBooksRepo.findBookByUserId(userId);
        if (rentedBooks.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No rented books found");
        }
        for (RentedBooksDto rentedBook : rentedBooks) {
            JSONObject json = new JSONObject();
            json.put("WhoBorrowed", rentedBook.getUser().getFirstName() + " " + rentedBook.getUser().getLastName());
            json.put("BookName", rentedBook.getBooksRefDto().getBook().getTitle());
            json.put("OwnerName", rentedBook.getBooksRefDto().getUser().getFirstName() + " " + rentedBook.getBooksRefDto().getUser().getLastName());
            json.put("RemainingDays", rentedBook.getRemainingDays());
            jsonObject.add(json);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jsonObject.toString());
    }
    public ResponseEntity<?> extendTimeForBook(UUID rentedBookId, DefaultPeriodsForExtendedTime body) {
        RentedBooksDto rentedBook = rentedBooksRepo.findByRentedBookId(rentedBookId);
        if (rentedBook == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Book not found");
        } else {
            LocalDate extendedTime = LocalDate.now().plus(body.getPeriod());
            long extendedDays = Period.between(LocalDate.now(), extendedTime).getDays();
            long remainingDaysAfterExtension = rentedBook.getRemainingDays() + extendedDays;
            rentedBook.setRemainingDays(remainingDaysAfterExtension);
            rentedBooksRepo.save(rentedBook);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Time extended");
        }
    }

    public ResponseEntity<?> getRentedBookByUserId(UUID userId) {
        List<JSONObject> jsonObject = new ArrayList<>();
        List<RentedBooksDto> rentedBook = rentedBooksRepo.findRentedBookByUserId(userId);
        if (rentedBook == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Book not found");
        } else {
            for (RentedBooksDto book : rentedBook) {
                JSONObject json = new JSONObject();
                json.put("BookTitle", book.getBooksRefDto().getBook().getTitle());
                json.put("BookAuthor", book.getBooksRefDto().getBook().getAuthor());
                json.put("RemainingDays", book.getRemainingDays());
                jsonObject.add(json);
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jsonObject.toString());
        }
    }
}
