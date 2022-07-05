package com.endava.models;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "waiting_list")
public class WaitingListDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiting_id")
    private UUID waitingId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserDto user;

    @JoinColumn(name = "book_ref_id")
    @ManyToOne
    private BooksRefDto  booksRefDto;

    public UUID getWaitingId() {
        return waitingId;
    }

    public void setWaitingId(UUID waitingId) {
        this.waitingId = waitingId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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
                ", userId=" + getUser().getUserId() +
                ", booksRefDtoId=" + getBooksRefDto().getBookRefId() +
                '}';
    }
}
