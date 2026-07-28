package co.edu.unicauca.child_programming_backend.DTO.output.full;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.unicauca.child_programming_backend.DTO.output.PracticeDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.RoleDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.ThinkletDTORespuesta;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildActivityFullDTORespuesta {

    private Integer id_activity;
    private String name_activity;
    private String description_activity;

    //Información de la ronda padre
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer parent_round_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String parent_round_name;

    // Relaciones completas
    private PracticeDTORespuesta practice;
    private ThinkletDTORespuesta thinklet;

    // Roles asignados
    private List<RoleDTORespuesta> assignedRoles;
}
