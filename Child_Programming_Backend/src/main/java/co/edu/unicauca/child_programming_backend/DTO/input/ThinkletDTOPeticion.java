package co.edu.unicauca.child_programming_backend.DTO.input;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ThinkletDTOPeticion {
    //private Integer id_thinklet;
    private String name_thinklet;
    private String description_thinklet;
    private Integer id_pattern;

    public ThinkletDTOPeticion(){}
}
