package com.sjkim.springbootjpa.domain;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    private void prePersist() {
        log.info(">> prePersist");
        this.createdAt = LocalDateTime.now();
        this.updatedAt = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        log.info(">> preUpdate");
        this.updatedAt = new Date();
    }
}
