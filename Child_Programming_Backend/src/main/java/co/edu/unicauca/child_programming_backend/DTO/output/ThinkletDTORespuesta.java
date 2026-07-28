package co.edu.unicauca.child_programming_backend.DTO.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ThinkletDTORespuesta {
    private Integer id_thinklet;
    private String name_thinklet;
    private String description_thinklet;
    //devolvemos un objeto con más información del patron
    private CollaborativePatternDTORespuesta pattern;
    //private String name_pattern; // Solo mostramos el nombre del patrón asociado

    public ThinkletDTORespuesta() {}
}