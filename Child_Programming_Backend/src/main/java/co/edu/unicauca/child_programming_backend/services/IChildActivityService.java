package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.ChildActivityDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.ChildActivityDTORespuesta;

import java.util.List;

public interface IChildActivityService {
    
    // Crear una nueva actividad
    ChildActivityDTORespuesta createActivity(ChildActivityDTOPeticion activity);

    // Modificar una actividad existente
    ChildActivityDTORespuesta updateActivity(Integer id, ChildActivityDTOPeticion activity);

    // Eliminar una actividad por ID
    boolean deleteActivity(Integer id);

    // Listar todas las actividades
    List<ChildActivityDTORespuesta> getAllActivities();

    // Consultar una actividad específica por ID
    ChildActivityDTORespuesta getActivityById(Integer id);

    //obtener roles asignados a una actividad
    ChildActivityDTORespuesta getActivityWithRoles(Integer idActivity);

}