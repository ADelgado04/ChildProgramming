package co.edu.unicauca.child_programming_backend.DTO.output;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChildActivityDTORespuesta {

    private Integer id_activity;
    private String name_activity;
    private String description_activity;

    //IDs de las relaciones
    private Integer id_process;
    private Integer id_practice;
    private Integer id_thinklet;
    // Información más descriptiva de las relaciones
    private String name_process;
    private String name_practice;
    private String name_thinklet;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer parent_round_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String parent_round_name;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)//evita enviar listas vacías si no se necesitan.
    private List<RoleDTORespuesta> assignedRoles;

    public ChildActivityDTORespuesta() {}
}

