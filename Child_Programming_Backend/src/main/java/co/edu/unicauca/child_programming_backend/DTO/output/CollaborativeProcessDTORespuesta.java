package co.edu.unicauca.child_programming_backend.DTO.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CollaborativeProcessDTORespuesta {
    private Integer id_process;
    private String name_process;
    private String description_process;
    private String version_process;
    private String image;

}
