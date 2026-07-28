package co.edu.unicauca.child_programming_backend.DTO.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PracticeDTORespuesta {
    private Integer id_practice;
    private String name_practice;
    private String description_practice;
    private String type_practice;

    public PracticeDTORespuesta() {}
}
