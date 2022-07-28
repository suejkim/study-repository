package com.sjkim.springbootjpa.domain.listener;

import java.time.LocalDateTime;
import java.util.Date;

public interface Auditable {
    LocalDateTime getCreatedAt();
    Date getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(Date updatedAt);

}
