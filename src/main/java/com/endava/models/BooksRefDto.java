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
    @ManyToOne(fetch = FetchType.LAZY)
    private BookDto book;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
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

    public UUID getBookRefId() {
        return bookRefId;
    }

    public void setBookRefId(UUID bookRefId) {
        this.bookRefId = bookRefId;
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
}
