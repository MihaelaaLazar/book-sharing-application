package com.endava.models;

import javax.persistence.*;
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
    private Long returningDate;
    public RentedBooksDto() {}

    public RentedBooksDto(UUID rentedBookId, UUID userId, UUID bookRefId, long remainingDays) {
        this.rentedBookId = rentedBookId;
        this.user = new UserDto(userId);
        this.bookForRent = bookForRent.getBookRef().setBookRefId(bookRefId);
        this.returningDate = remainingDays;
    }

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

    public Long getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(Long returningDate) {
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