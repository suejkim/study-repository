package com.sjkim.springbootjpa.domain.listener;

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

    @PrePersist // 리스너(엔티티에 직접 적용)
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

    @PostLoad
    private void postLoad() {
        log.info(">> postLoad");
    }

    @PreRemove
    private void preRemove() {
        log.info(">> preRemove");
    }

    @PostPersist
    private void postPersist() {
        log.info(">> postPersist");
    }

    @PostUpdate
    private void postUpdate() {
        log.info(">> postUpdate");
    }

    @PostRemove
    private void postRemove() {
        log.info(">> postRemove");
    }
}
