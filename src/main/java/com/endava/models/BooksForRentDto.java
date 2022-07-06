package com.endava.models;

import javax.persistence.*;
import java.util.List;
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


    public BooksForRentDto(UUID bookForRentId, BooksRefDto bookRef) {
        this.bookForRentId = bookForRentId;
        this.bookRef = bookRef;
    }
    public BooksForRentDto() {}

    public BooksForRentDto(UUID bookForRentId){
        this.bookForRentId = bookForRentId;
    }

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

    @Override
    public String toString() {
        return "BooksForRentDto{" +
                "bookForRentId=" + getBookForRentId() +
                ", bookRefId=" + getBookRef().getBookRefId() +
                '}';
    }
}
