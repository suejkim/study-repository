package com.sjkim.springbootjpa.domain;

import com.sjkim.springbootjpa.domain.listener.BaseEntityForAuditing;
import com.sjkim.springbootjpa.domain.listener.BaseEntityForEntityListeners;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
//@EntityListeners(value = BaseEntityForEntityListeners.class) // 커스텀 리스너
//public class Author implements Auditable {
public class Author extends BaseEntityForAuditing {
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
