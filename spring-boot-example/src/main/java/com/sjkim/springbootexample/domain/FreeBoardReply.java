package com.sjkim.springbootexample.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "free_board_reply")
@NoArgsConstructor
public class FreeBoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reply;
    private String replyer;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    private FreeBoard freeBoard;

    @Builder
    public FreeBoardReply(Long id, String reply, String replyer, LocalDateTime createdAt, LocalDateTime updatedAt, FreeBoard freeBoard) {
        this.id = id;
        this.reply = reply;
        this.replyer = replyer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.freeBoard = freeBoard;
    }
}
