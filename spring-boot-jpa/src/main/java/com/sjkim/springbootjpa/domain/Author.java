package com.sjkim.springbootjpa.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
public class Author extends BaseEntity {
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
}
