package com.sjkim.springbootjpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
//@EntityListeners(value = BaseEntityForEntityListeners.class) // 커스텀 리스너
@EntityListeners(value = AuditingEntityListener.class) // Spring에서 제공
public class Author implements Auditable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "comment", columnDefinition = "longtext")
    private String comment;

    // for @EntityListeners Test
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Builder
    public Author(Long id, List<Book> books, String name, String comment) {
        this.id = id;
        this.books = books;
        this.name = name;
        this.comment = comment;
    }

    public Author updateComment(String comment) {
        this.comment = comment;
        return this;
    }
}
