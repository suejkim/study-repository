package com.sjkim.springboottransaction.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "board")
//@DynamicUpdate
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String writer;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "board_id", foreignKey = @ForeignKey(name = "board_id"))
//    private List<BoardFile> boardFiles;

    @Builder
    public Board(Long id, String title, String content, String writer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Board setTitle(String title) {
        this.title = title;
        return this;
    }
}


