package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.CollaborativePatternDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.CollaborativePatternDTORespuesta;

import java.util.List;

public interface ICollaborativePatternService {
    
    // Crear un nuevo patrón colaborativo
    CollaborativePatternDTORespuesta createPattern(CollaborativePatternDTOPeticion request);

    // Modificar un patrón existente
    CollaborativePatternDTORespuesta updatePattern(Integer id, CollaborativePatternDTOPeticion request);

    // Eliminar un patrón por ID
    boolean deletePattern(Integer id);

    // Listar todos los patrones
    List<CollaborativePatternDTORespuesta> getAllPatterns();

    // Consultar un patrón específico por ID
    CollaborativePatternDTORespuesta getPatternById(Integer id);
}

