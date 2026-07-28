package co.edu.unicauca.child_programming_backend.DTO.output.full;

import java.util.List;

import co.edu.unicauca.child_programming_backend.DTO.output.RoundDTORespuesta;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessFullDTORespuesta {

    // Datos del proceso
    private Integer id_process;
    private String name_process;
    private String description_process;
    private String version_process;
    private String image;

    //Lista de rondas del proceso
    private List<RoundDTORespuesta> rounds;

    //Lista de actividades del proceso
    private List<ChildActivityFullDTORespuesta> activities;
}
