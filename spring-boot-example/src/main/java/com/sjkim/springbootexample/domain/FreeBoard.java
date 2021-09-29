package com.sjkim.springbootexample.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Entity
@Table(name = "free_board")
@NoArgsConstructor
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "freeBoard")
    private List<FreeBoardReply> freeBoardReplies;

    @Builder
    public FreeBoard(Long id, String title, String writer, String content, LocalDateTime createdAt, LocalDateTime updatedAt, List<FreeBoardReply> freeBoardReplies) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.freeBoardReplies = freeBoardReplies;
    }
}
