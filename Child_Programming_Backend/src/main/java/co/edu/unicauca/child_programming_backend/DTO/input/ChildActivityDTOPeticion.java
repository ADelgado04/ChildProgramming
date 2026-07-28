package co.edu.unicauca.child_programming_backend.DTO.input;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ChildActivityDTOPeticion {
    //private Integer id_activity;
    private String name_activity;
    private String description_activity;
    private Integer id_practice;
    private Integer id_thinklet;
    private Integer id_process;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer parent_round_id;

    public ChildActivityDTOPeticion(){}
}
