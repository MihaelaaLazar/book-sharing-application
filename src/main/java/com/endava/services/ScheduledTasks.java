package com.endava.services;


import com.endava.models.*;
import com.endava.repositories.BooksForRentRepo;
import com.endava.repositories.RentedBooksRepo;
import com.endava.repositories.WaitingListRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private LocalDate DEFAULT_RENT_VALUE = LocalDate.now().plusDays(1);
    @Autowired
    private RentedBooksRepo rentedBooksRepo;
    @Autowired
    private BooksForRentRepo booksForRentRepo;

    @Autowired
    private WaitingListRepo waitingListRepo;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //1 minute just for testing purposes
    @Scheduled(fixedRate = 60000)
    public void deleteTheBookFromTheRentedBooks() {
        List<RentedBooksDto> rentedBooksDtoList = rentedBooksRepo.findAll();
        for (RentedBooksDto rentedBooksDto : rentedBooksDtoList) {
            if (LocalDate.now().isAfter(rentedBooksDto.getReturningDate())) {
                rentedBooksRepo.delete(rentedBooksDto);
                booksForRentRepo.save(new BooksForRentDto(null, rentedBooksDto.getBooksRefDto()));
            }
        }
        log.info("Update rented books task ran at {}", dateFormat.format(new Date()));
    }

    //1 minute just for testing purposes
    @Scheduled(fixedRate = 65000)
    public void assignTheBookToTheUser() {
        List<WaitingListDto> usersInWaitingList = waitingListRepo.findAll();
        for (WaitingListDto _usersInWaitingList : usersInWaitingList) {
            BooksForRentDto booksForRentDto = booksForRentRepo.findByBookId(_usersInWaitingList.getBooksRefDto().getBook().getBookId());
            if (booksForRentDto != null) {
                RentedBooksDto rentedBook = new RentedBooksDto(null, _usersInWaitingList.getUserId(), booksForRentDto.getBookRef().getBookRefId(), DEFAULT_RENT_VALUE);
                rentedBooksRepo.save(rentedBook);
                booksForRentRepo.delete(booksForRentDto);
                waitingListRepo.delete(_usersInWaitingList);
            }
        }
        log.info("Assign available books task ran at {}", dateFormat.format(new Date()));
    }

}
