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
    @ManyToOne
    private BooksForRentDto bookForRent;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserDto user;

    @Column(name = "returning_date")
    private Period returningDate;

    public UUID getRentedBookId() {
        return rentedBookId;
    }

    public void setRentedBookId(UUID rentedBookId) {
        this.rentedBookId = rentedBookId;
    }

    public BooksForRentDto getBookForRent() {
        return bookForRent;
    }

    public void setBookForRent(BooksForRentDto bookForRent) {
        this.bookForRent = bookForRent;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Period getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(Period returningDate) {
        this.returningDate = returningDate;
    }

    @Override
    public String toString() {
        return "RentedBooksDto{" +
                "rentedBookId=" + getRentedBookId() +
                ", bookForRentId=" + getBookForRent().getBookForRentId() +
                ", userId=" + getUser().getUserId() +
                ", returningDate=" + getReturningDate() +
                '}';
    }
}
