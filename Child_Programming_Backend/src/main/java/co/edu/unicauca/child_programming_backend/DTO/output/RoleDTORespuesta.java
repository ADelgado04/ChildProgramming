package co.edu.unicauca.child_programming_backend.DTO.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class RoleDTORespuesta {
    private Integer id_role;
    private String name_role;
    private String description_role;
    private String skills_role;
}
