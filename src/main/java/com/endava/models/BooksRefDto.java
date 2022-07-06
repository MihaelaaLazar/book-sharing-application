package com.endava.models;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "books_ref")
@NoArgsConstructor
public class BooksRefDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_ref_id")
    private UUID bookRefId;

    @JoinColumn(name = "book_id")
    @ManyToOne
    private BookDto book;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserDto user;

    public BooksRefDto(UUID bookRefId, BookDto book, UserDto user) {
        this.bookRefId = bookRefId;
        this.book = book;
        this.user = user;
    }

    public BooksRefDto(UUID bookRefId, UUID userId, UUID bookId) {
        this.bookRefId = bookRefId;
        this.user = new UserDto(userId);
        this.book = new BookDto(bookId);
    }

    public BooksRefDto(UUID bookRefId) {
        this.bookRefId = bookRefId;
    }

    public UUID getBookRefId() {
        return bookRefId;
    }

    public BooksForRentDto setBookRefId(UUID bookRefId) {
        this.bookRefId = bookRefId;
        return null;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BooksRefDto{" +
                "bookRefId=" + getBookRefId() +
                ", bookId=" + getBook() +
                ", userId=" + getUser().getUserId() +
                '}';
    }
}
