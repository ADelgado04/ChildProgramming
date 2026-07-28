package co.edu.unicauca.child_programming_backend.DTO.input;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class RoundDTOPeticion extends ChildActivityDTOPeticion{
    
    private String round_status;

    public RoundDTOPeticion(){
        super();
    }
}
