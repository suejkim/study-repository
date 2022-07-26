package com.sjkim.springbootjpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table
@NoArgsConstructor
public class UserHistory extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            targetEntity = User.class,
            optional = false // not null -> 내부조인
//            , cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_USER", value = ConstraintMode.PROVIDER_DEFAULT)
    )
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private ActionType type;
    // insertable = false: insert 시 type은 제외됨
    // Hibernate:
    // insert
    //    into
    //        user_history
    //        (created_at, updated_at, user_id)
    //    values
    //        (?, ?, ?)

    // updatable = false: update 시 type은 제외됨
    // Hibernate:
    //    update
    //        user_history
    //    set
    //        created_at=?,
    //        updated_at=?,
    //        user_id=?
    //    where
    //        id=?

    @Builder
    public UserHistory(User user, ActionType type) {
        this.user = user;
        this.type = type;
    }

    // updatable 테스트용
    public UserHistory updateUserAndActionType(User user, ActionType type) {
        this.user = user;
        this.type = type;
        return this;
    }

    public UserHistory updateUser(User user) {
        this.user = user;
        return this;
    }
}
