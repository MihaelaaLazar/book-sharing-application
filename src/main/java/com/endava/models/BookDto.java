package com.endava.models;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "books")
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BookDto(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookDto() {
    }

    public BookDto(UUID bookId, String title, String author, LocalDate dateOfPublication, String description) {
        this.bookId = getBookId();
        this.title = getTitle();
        this.author = getAuthor();
        this.dateOfPublication = getDateOfPublication();
        this.description = getDescription();
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + getBookId() +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", dateOfPublication=" + getDateOfPublication() +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}