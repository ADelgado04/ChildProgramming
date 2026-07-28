package co.edu.unicauca.child_programming_backend.DTO.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class CollaborativePatternDTOPeticion {
    //private Integer id_pattern;
    private String name_pattern;
    private String description_pattern;

    public CollaborativePatternDTOPeticion(){
    }
}
