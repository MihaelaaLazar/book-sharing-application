package com.endava.services;


import com.endava.utils.DefaultPeriodsForExtendedTime;
import com.endava.models.RentedBooksDto;
import com.endava.repositories.RentedBooksRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
                    .status(404)
                    .body("No rented books found");
        }
        for (RentedBooksDto rentedBook : rentedBooks) {
            JSONObject json = new JSONObject();
            json.put("whoBorrowed", rentedBook.getUser().getFirstName() + " " + rentedBook.getUser().getLastName());
            json.put("bookName", rentedBook.getBooksRefDto().getBook().getTitle());
            json.put("ownerName", rentedBook.getBooksRefDto().getUser().getFirstName() + " " + rentedBook.getBooksRefDto().getUser().getLastName());
            json.put("returningDate", rentedBook.getReturningDate());
            jsonObject.add(json);
        }
        return ResponseEntity
                .status(200)
                .body(jsonObject.toString());
    }

    public ResponseEntity<?> extendTimeForBook(UUID rentedBookId, DefaultPeriodsForExtendedTime body) {
        RentedBooksDto rentedBook = rentedBooksRepo.findByRentedBookId(rentedBookId);
        if (rentedBook == null) {
            return ResponseEntity
                    .status(404)
                    .body("Book not found");
        } else if (rentedBook.isExtended()) {
            return ResponseEntity
                    .status(400)
                    .body("Book already extended");
        } else {
            LocalDate extendedTime = LocalDate.now().plus(body.getPeriod());
            Period extendedDays = Period.between(LocalDate.now(), extendedTime);
            LocalDate returningDateAfterExtension = rentedBook.getReturningDate().plus(extendedDays);
            rentedBook.setReturningDate(returningDateAfterExtension);
            rentedBook.setExtended(true);
            rentedBooksRepo.save(rentedBook);
            return ResponseEntity
                    .status(200)
                    .body("Time extended");
        }
    }

    public ResponseEntity<?> getRentedBookByUserId(UUID userId) {
        List<JSONObject> jsonObject = new ArrayList<>();
        List<RentedBooksDto> rentedBook = rentedBooksRepo.findRentedBookByUserId(userId);
        if (rentedBook == null) {
            return ResponseEntity
                    .status(404)
                    .body("Book not found");
        } else {
            for (RentedBooksDto book : rentedBook) {
                JSONObject json = new JSONObject();
                json.put("bookTitle", book.getBooksRefDto().getBook().getTitle());
                json.put("bookAuthor", book.getBooksRefDto().getBook().getAuthor());
                json.put("returningDate", book.getReturningDate());
                json.put("rentedId", book.getRentedBookId());
                json.put("bookOwner", book.getBooksRefDto().getUser().getUserId());
                jsonObject.add(json);
            }
            return ResponseEntity
                    .status(200)
                    .body(jsonObject.toString());
        }
    }
}
