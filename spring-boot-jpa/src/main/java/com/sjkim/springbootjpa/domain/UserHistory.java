package com.sjkim.springbootjpa.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
public class UserHistory extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class,
            optional = false // not null
//            , cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_USER", value = ConstraintMode.PROVIDER_DEFAULT)
    ) // Option
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private ActionType type;

    @Builder
    public UserHistory(User user, ActionType type) {
        this.user = user;
        this.type = type;
    }
}
