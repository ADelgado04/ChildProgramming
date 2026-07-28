package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.ThinkletDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.ThinkletDTORespuesta;

import java.util.List;

public interface IThinkletService {
    
    // Crear un nuevo thinklet
    ThinkletDTORespuesta createThinklet(ThinkletDTOPeticion request);

    // Modificar un thinklet existente
    ThinkletDTORespuesta updateThinklet(Integer id, ThinkletDTOPeticion request);

    // Eliminar un thinklet por ID
    boolean deleteThinklet(Integer id);

    // Listar todos los thinklets
    List<ThinkletDTORespuesta> getAllThinklets();

    // Buscar un thinklet por ID
    ThinkletDTORespuesta getThinkletById(Integer id);
}

