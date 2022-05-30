package com.sjkim.springbootjpa.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
public class BookInfo {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(optional = false)
    private Book book;

    @Column(name = "sell_count", nullable = false)
    private long sellCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;
}
