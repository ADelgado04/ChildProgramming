package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.RoundDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.RoundDTORespuesta;

import java.util.List;

public interface IRoundService {

    // Crear un nuevo round
    RoundDTORespuesta createRound(RoundDTOPeticion round);

    // Modificar un round existente
    RoundDTORespuesta updateRound(Integer id, RoundDTOPeticion round);

    // Eliminar un round por ID
    boolean deleteRound(Integer id);

    // Listar todos los rounds
    List<RoundDTORespuesta> getAllRounds();

    // Consultar un round específico por ID
    RoundDTORespuesta getRoundById(Integer id);

    // Consultar rondas asociadas a un proceso
    List<RoundDTORespuesta> getRoundsByProcess(Integer idProcess);
}
