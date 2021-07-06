package domain.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class SingerGroup {

    private long id;
    private String name;
    private LocalDate debutDate;
    private String agency;

    @Builder
    public SingerGroup(long id, String name, LocalDate debutDate, String agency) {
        this.id = id;
        this.name = name;
        this.debutDate = debutDate;
        this.agency = agency;
    }
}
