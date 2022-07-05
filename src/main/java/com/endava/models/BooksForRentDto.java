package com.endava.models;


import javax.persistence.*;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "books_for_rent")
public class BooksForRentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_for_rent_id")
    private UUID bookForRentId;

    @JoinColumn(name = "book_ref_id")
    @ManyToOne
    private BooksRefDto bookRef;

    @Column(name = "rent_date")
    private Period rentDate;

    public UUID getBookForRentId() {
        return bookForRentId;
    }

    public void setBookForRentId(UUID bookForRentId) {
        this.bookForRentId = bookForRentId;
    }

    public BooksRefDto getBookRef() {
        return bookRef;
    }

    public void setBookRef(BooksRefDto bookRef) {
        this.bookRef = bookRef;
    }

    public Period getRentDate() {
        return rentDate;
    }

    public void setRentDate(Period rentDate) {
        this.rentDate = rentDate;
    }

    @Override
    public String toString() {
        return "BooksForRentDto{" +
                "bookForRentId=" + getBookForRentId() +
                ", bookRefId=" + getBookRef().getBookRefId() +
                ", rentDate=" + getRentDate() +
                '}';
    }
}
