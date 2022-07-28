package com.endava.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "books_for_rent")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BooksForRentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_for_rent_id")
    private UUID bookForRentId;

    @JoinColumn(name = "book_ref_id")
    @ManyToOne
    private BooksRefDto bookRef;

}
