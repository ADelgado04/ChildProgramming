package co.edu.unicauca.child_programming_backend.DTO.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class CollaborativeProcessDTOPeticion {
    //private Integer id_process;
    private String name_process;
    private String description_process;
    private String version_process;
    private String image;

    public CollaborativeProcessDTOPeticion(){}
}
