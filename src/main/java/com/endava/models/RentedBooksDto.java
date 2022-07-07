package com.endava.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rented_books")
public class RentedBooksDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rentedBookId;

    @JoinColumn(name = "book_ref_id")
    @ManyToOne
    private BooksRefDto booksRefDto;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserDto user;

    @Column(name = "returning_date")
    private Long remainingDays;
    public RentedBooksDto() {}

    public RentedBooksDto(UUID rentedBookId, UUID userId, UUID bookRefId, long remainingDays) {
        this.rentedBookId = rentedBookId;
        this.user = new UserDto(userId);
        this.booksRefDto = new BooksRefDto(bookRefId);
        this.remainingDays = remainingDays;
    }

    public UUID getRentedBookId() {
        return rentedBookId;
    }

    public void setRentedBookId(UUID rentedBookId) {
        this.rentedBookId = rentedBookId;
    }

    public BooksRefDto getBooksRefDto() {
        return booksRefDto;
    }

    public void setBooksRefDto(BooksRefDto booksRefDto) {
        this.booksRefDto = booksRefDto;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Long getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(Long remainingDays) {
        this.remainingDays = remainingDays;
    }

    @Override
    public String toString() {
        return "RentedBooksDto{" +
                "rentedBookId=" + getRentedBookId() +
                ", bookRefId=" + getBooksRefDto().getBookRefId() +
                ", userId=" + getUser().getUserId() +
                ", returningDate=" + getRemainingDays() +
                '}';
    }
}