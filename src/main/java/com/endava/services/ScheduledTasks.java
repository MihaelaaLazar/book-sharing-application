package com.endava.services;


import com.endava.models.BooksForRentDto;
import com.endava.models.RentedBooksDto;
import com.endava.repositories.BooksForRentRepo;
import com.endava.repositories.RentedBooksRepo;
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
    @Autowired
    private RentedBooksRepo rentedBooksRepo;
    @Autowired
    private BooksForRentRepo booksForRentRepo;

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
        log.info("The task ran at {}", dateFormat.format(new Date()));
    }

}
