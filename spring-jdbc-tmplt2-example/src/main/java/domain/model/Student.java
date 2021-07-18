package domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Student {

    private long id;
    private String name;
    private int age;
    private LocalDate birth;
    private String phone;

    @Builder
    public Student(String name, int age, LocalDate birth, String phone) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.phone = phone;
    }
}
