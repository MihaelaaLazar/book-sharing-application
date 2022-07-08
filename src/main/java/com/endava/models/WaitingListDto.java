package com.endava.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "waiting_list")
public class WaitingListDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "waiting_id")
    private UUID waitingId;

    @Column(name = "user_id")
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @JoinColumn(name = "book_ref_id")
    @ManyToOne
    private BooksRefDto booksRefDto;

    public WaitingListDto(UUID waitingId, UUID userId, UUID bookRefId) {
        this.waitingId = waitingId;
        this.userId = userId;
        this.booksRefDto = new BooksRefDto(bookRefId);
    }

    public WaitingListDto() {
    }

    public UUID getWaitingId() {
        return waitingId;
    }

    public void setWaitingId(UUID waitingId) {
        this.waitingId = waitingId;
    }

    public BooksRefDto getBooksRefDto() {
        return booksRefDto;
    }

    public void setBooksRefDto(BooksRefDto booksRefDto) {
        this.booksRefDto = booksRefDto;
    }

    @Override
    public String toString() {
        return "WaitingListDto{" +
                "waitingId=" + getWaitingId() +
                ", userId=" + getUserId() +
                ", booksRefDtoId=" + getBooksRefDto().getBookRefId() +
                '}';
    }
}