package domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
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
