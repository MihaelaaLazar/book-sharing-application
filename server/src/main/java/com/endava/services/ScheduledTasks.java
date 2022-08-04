package com.endava.services;


import com.endava.models.*;
import com.endava.repositories.BooksForRentRepo;
import com.endava.repositories.RentedBooksRepo;
import com.endava.repositories.WaitingListRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.endava.utils.Constants.DATE_FORMAT;
import static com.endava.utils.Constants.DEFAULT_RENT_VALUE;

@Component
@Slf4j
public class ScheduledTasks {
    @Autowired
    private RentedBooksRepo rentedBooksRepo;
    @Autowired
    private BooksForRentRepo booksForRentRepo;

    @Autowired
    private WaitingListRepo waitingListRepo;

    @Scheduled(cron = "0 0 7 * * *" , zone = "Europe/Bucharest")
    public void deleteTheBookFromTheRentedBooks() {
        List<RentedBooksDto> rentedBooksDtoList = rentedBooksRepo.findAll();
        for (RentedBooksDto rentedBooksDto : rentedBooksDtoList) {
            if (LocalDate.now().isAfter(rentedBooksDto.getReturningDate())) {
                rentedBooksRepo.delete(rentedBooksDto);
                booksForRentRepo.save(new BooksForRentDto(null, rentedBooksDto.getBookRef()));
            }
        }
        log.info("Update rented books task ran at {}", DATE_FORMAT.format(new Date()));
    }

    @Scheduled(cron = "0 0 8 * * *" , zone = "Europe/Bucharest")
    public void assignTheBookToTheUser() {
        List<WaitingListDto> usersInWaitingList = waitingListRepo.findAll();
        for (WaitingListDto _usersInWaitingList : usersInWaitingList) {
            BooksForRentDto booksForRentDto = booksForRentRepo.findByBookId(_usersInWaitingList.getBookRef().getBook().getBookId());
            if (booksForRentDto != null) {
                RentedBooksDto rentedBook = new RentedBooksDto(
                        null,
                        _usersInWaitingList.getUserId(),
                        booksForRentDto.getBookRef().getBookRefId(),
                        DEFAULT_RENT_VALUE);
                rentedBooksRepo.save(rentedBook);
                booksForRentRepo.delete(booksForRentDto);
                waitingListRepo.delete(_usersInWaitingList);
            }
        }
        log.info("Assign available books task ran at {}", DATE_FORMAT.format(new Date()));
    }

}
