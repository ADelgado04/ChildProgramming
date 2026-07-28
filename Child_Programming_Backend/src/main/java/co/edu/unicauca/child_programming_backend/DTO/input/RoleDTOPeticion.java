package co.edu.unicauca.child_programming_backend.DTO.input;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleDTOPeticion {

    private String name_role;
    private String description_role;
    private String skills_role;

    public RoleDTOPeticion(){}
}
