package com.endava.services;

import com.endava.models.*;
import com.endava.repositories.RentedBooksRepo;
import com.endava.repositories.WaitingListRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WaitingListService {

    @Autowired
    private WaitingListRepo waitingListRepo;

    @Autowired
    private RentedBooksRepo rentedBooksRepo;

    public ResponseEntity<?> addUserToWaitingList(UUID userId, UUID bookId) {
        RentedBooksDto rentedBooks = rentedBooksRepo.findOneBookByBookId(bookId);
        if (rentedBooks == null) {
            return ResponseEntity
                    .status(404)
                    .body("Book not found");
        }
        if (rentedBooks.getBookRef().getUser().getUserId().equals(userId) ||
                rentedBooks.getUser().getUserId().equals(userId)) {
            return ResponseEntity
                    .status(409)
                    .body("User not allowed to add to waiting list");
        }

        if (waitingListRepo.findByUserId(userId).isPresent()) {
            return ResponseEntity
                    .status(400)
                    .body("User already in waiting list");
        }

        waitingListRepo.save(new WaitingListDto(UUID.randomUUID(), userId, rentedBooks.getBookRef().getBookRefId()));
        return ResponseEntity
                .status(200)
                .body("User added to waiting list");
    }

    public List<WaitingListDto> getAllUsers() {
        return waitingListRepo.findAll();
    }

    public ResponseEntity<?> getByUserId(UUID userId) {
        JSONObject jsonObject = new JSONObject();
        if (waitingListRepo.findByUserId(userId).isEmpty()) {
            jsonObject.put("message", "User not found in waiting list");
            return ResponseEntity
                    .status(404)
                    .body(jsonObject.toString());
        } else {
            WaitingListDto waitingListDto = waitingListRepo.findByUserId(userId).orElseThrow(null);
            jsonObject.put("title", waitingListDto.getBookRef().getBook().getTitle());
            jsonObject.put("author", waitingListDto.getBookRef().getBook().getAuthor());
            jsonObject.put("description", waitingListDto.getBookRef().getBook().getDescription());
            jsonObject.put("imageUrl", waitingListDto.getBookRef().getBook().getImageUrl());
            RentedBooksDto rentedBook = rentedBooksRepo.findOneBookByBookId(waitingListDto.getBookRef().getBook().getBookId());
            jsonObject.put("bookOwner", rentedBook.getBookRef().getUser().getUsername());
            jsonObject.put("whoBorrowed", rentedBook.getUser().getUsername());
            jsonObject.put("returningDate", rentedBook.getReturningDate());
            return ResponseEntity
                    .status(200)
                    .body(jsonObject.toString());
        }
    }
}
