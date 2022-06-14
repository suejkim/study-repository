package com.sjkim.springbootjpa.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table
public class BookInfo extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    @Column(name = "sell_count", nullable = false)
    private long sellCount;

}
