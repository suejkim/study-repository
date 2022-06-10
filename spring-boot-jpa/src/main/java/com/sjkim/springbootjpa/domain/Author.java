package com.sjkim.springbootjpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Author extends BaseEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "comment", columnDefinition = "longtext")
    private String comment;

}
