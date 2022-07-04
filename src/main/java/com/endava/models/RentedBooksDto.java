package com.endava.models;

import javax.persistence.*;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "rented_books")
public class RentedBooksDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rentedBookId;

    @JoinColumn(name = "book_for_rent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BooksForRentDto bookForRent;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDto user;

    @Column(name = "returning_date")
    private Period returningDate;

}
