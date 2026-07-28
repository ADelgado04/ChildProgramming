package co.edu.unicauca.child_programming_backend.DTO.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.unicauca.child_programming_backend.domain.enums.RoundStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoundDTORespuesta extends ChildActivityDTORespuesta {
    
    private RoundStatus round_status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ChildActivityDTORespuesta> subActivities;

    public RoundDTORespuesta() {
        super();
    }
}
