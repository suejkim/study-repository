package com.sjkim.springbootjpa.domain;

import javax.persistence.*;

@Entity
@Table
public class BookInfo extends BaseEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(optional = false)
    private Book book;

    @Column(name = "sell_count", nullable = false)
    private long sellCount;

}
