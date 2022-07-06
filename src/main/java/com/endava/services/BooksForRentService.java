package com.endava.services;

import com.endava.models.BooksForRentDto;
import com.endava.models.RentalPeriod;
import com.endava.models.RentedBooksDto;
import com.endava.repositories.BookRefRepo;
import com.endava.repositories.BooksForRentRepo;
import com.endava.repositories.RentedBooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BookRefRepo bookRefRepo;


    public List<BooksForRentDto> getBooksForRent(){
        return booksForRentRepo.findAll();
    }

    public void rentBook(UUID userId, UUID bookRefId, RentalPeriod body) {
        BooksForRentDto bookAvailable = booksForRentRepo.findByBookRefId(bookRefId).orElse(null);
        if (bookAvailable == null) {
            throw new IllegalArgumentException("Book not available");
        }

        LocalDate date = LocalDate.now().plus(body.getPeriod());
        long remainingDays = Period.between(LocalDate.now(), date).getDays();

        RentedBooksDto rentedBook = new RentedBooksDto(null, userId, bookAvailable.getBookForRentId() , remainingDays);
        rentedBooksRepo.save(rentedBook);
//        booksForRentRepo.delete(bookAvailable);
    }
}
