package com.endava.models;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "books_ref")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
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


    public BooksRefDto(UUID bookRefId) {
        this.bookRefId = bookRefId;
    }

    public BooksRefDto(UUID bookRefId, UUID userId, UUID bookId) {
        this.bookRefId = bookRefId;
        this.user = new UserDto(userId);
        this.book = new BookDto(bookId);
    }
}
