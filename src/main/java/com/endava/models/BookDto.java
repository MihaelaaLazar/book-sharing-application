package com.endava.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    @Schema(hidden = true)
    private UUID bookId;

    @Column(name = "title")
    @Schema(description = "Title" ,required = true, example = "The Lord of the Rings")
    private String title;

    @Column(name = "author")
    @Schema(description = "Author" ,required = true, example = "J.R.R. Tolkien")
    private String author;

    @Column(name = "date_of_publication")
    @Schema(description = "Date of publication" ,required = true, example = "1999-12-12")
    private LocalDate dateOfPublication;

    @Column(name = "description")
    @Schema(description = "Description" ,required = true, example = "The Lord of the Rings is the saga of a group of sometimes reluctant heroes who set forth to save their world from consummate evil. Its many worlds and creatures were drawn from Tolkien's extensive knowledge of philology and folklore.")
    private String description;

    @Column(name = "image_url")
    @Schema(hidden = true)
    private String imageUrl;

    public BookDto(UUID bookId) {
        this.bookId = bookId;
    }

}