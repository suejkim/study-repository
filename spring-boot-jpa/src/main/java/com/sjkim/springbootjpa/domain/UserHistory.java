package com.sjkim.springbootjpa.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
public class UserHistory {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = User.class,
            optional = false // not null
    )
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_USER", value = ConstraintMode.PROVIDER_DEFAULT)
    ) // Option
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private ActionType type;

    // 중복되어 리팩토링 필요
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

}
