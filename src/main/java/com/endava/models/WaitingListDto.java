package com.endava.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "waiting_list")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
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
    private BooksRefDto bookRef;

    public WaitingListDto(UUID waitingId, UUID userId, UUID bookRefId) {
        this.waitingId = waitingId;
        this.userId = userId;
        this.bookRef = new BooksRefDto(bookRefId);
    }
}