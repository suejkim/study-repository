package domain.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Singer {

    private long id;
    private String name;
    private LocalDate birth;
    private Sex sex;
    private long groupId;

    @Builder
    public Singer(long id, String name, LocalDate birth, Sex sex, long groupId) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.sex = sex;
        this.groupId = groupId;
    }
}
