package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.PracticeDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.PracticeDTORespuesta;

import java.util.List;

public interface IPracticeService {

    // Crear una nueva práctica
    PracticeDTORespuesta createPractice(PracticeDTOPeticion practice);

    // Modificar una práctica existente
    PracticeDTORespuesta updatePractice(Integer id, PracticeDTOPeticion practice);

    // Eliminar una práctica por ID
    boolean deletePractice(Integer id);

    // Listar todas las prácticas
    List<PracticeDTORespuesta> getAllPractices();

    // Consultar una práctica específica por ID
    PracticeDTORespuesta getPracticeById(Integer id);
}
