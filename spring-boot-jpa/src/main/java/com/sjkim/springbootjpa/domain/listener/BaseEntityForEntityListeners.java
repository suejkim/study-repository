package com.sjkim.springbootjpa.domain.listener;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
public class BaseEntityForEntityListeners {
    @PrePersist
    private void prePersist(Object obj) {
        log.info(">> prePersist");
        if (obj instanceof Auditable) {
            ((Auditable) obj).setCreatedAt(LocalDateTime.now());
            ((Auditable) obj).setUpdatedAt(new Date());
        }
    }

    @PreUpdate
    private void preUpdate(Object obj) {
        log.info(">> preUpdate");
        if (obj instanceof Auditable) {
            ((Auditable) obj).setUpdatedAt(new Date());
        }
    }
}
