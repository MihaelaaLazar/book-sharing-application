package com.endava.services;

import com.endava.models.BooksForRentDto;
import com.endava.utils.RentalPeriod;
import com.endava.models.RentedBooksDto;
import com.endava.repositories.BooksForRentRepo;
import com.endava.repositories.RentedBooksRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    public List<BooksForRentDto> getBooksForRent() {
        return booksForRentRepo.findAll();
    }

    public ResponseEntity<?> rentBook(UUID userId, UUID bookRefId, RentalPeriod body) {
        BooksForRentDto bookAvailable = booksForRentRepo.findByBookRefId(bookRefId).orElse(null);
        if (bookAvailable == null) {
            return ResponseEntity
                    .status(204)
                    .body("Book not available");
        }
        if (bookAvailable.getBookRef().getUser().getUserId().equals(userId)) {
            return ResponseEntity
                    .status(400)
                    .body("You can't rent your own book");
        } else {
            LocalDate date = LocalDate.now().plus(body.getPeriod());
            Period returningDate = Period.between(LocalDate.now(), date);
            LocalDate returningDateAfterExtension = LocalDate.now().plus(returningDate);
            RentedBooksDto rentedBook = new RentedBooksDto(null, userId, bookRefId, returningDateAfterExtension);
            rentedBooksRepo.save(rentedBook);
            booksForRentRepo.delete(bookAvailable);
            return ResponseEntity
                    .status(200)
                    .body("Book rented");
        }
    }
    public ResponseEntity<?> getAvailableBookWithPagination(int page, int pageSize) {
        JSONObject responseBody = new JSONObject();
        long count = booksForRentRepo.countByBookForRendId();
        Page<BooksForRentDto> availableBooks = booksForRentRepo.findAll(PageRequest.of(page, pageSize));
        responseBody.put("totalCount", count);
        responseBody.put("availableBook", availableBooks.getContent());
        return ResponseEntity
                .status(200)
                .body(responseBody.toString());
    }
}
