package com.endava.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "rented_books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentedBooksDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rentedBookId;

    @JoinColumn(name = "book_ref_id")
    @ManyToOne
    private BooksRefDto bookRef;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserDto user;

    @Column(name = "returning_date")
    private LocalDate returningDate;

    @Schema(hidden = true)
    private boolean extended;

    public RentedBooksDto(UUID rentedBookId, UUID userId, UUID bookRefId, LocalDate returningDate) {
        this.rentedBookId = rentedBookId;
        this.user = new UserDto(userId);
        this.bookRef = new BooksRefDto(bookRefId);
        this.returningDate = returningDate;
    }
}