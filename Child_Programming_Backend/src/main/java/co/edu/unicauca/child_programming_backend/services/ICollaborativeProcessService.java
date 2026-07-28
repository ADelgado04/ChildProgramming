package co.edu.unicauca.child_programming_backend.services;

import java.util.List;

import co.edu.unicauca.child_programming_backend.DTO.input.CollaborativeProcessDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.CollaborativeProcessDTORespuesta;
public interface ICollaborativeProcessService {

    // Crear un proceso (entrada = Petición, salida = Respuesta completa)
    public CollaborativeProcessDTORespuesta createProcess(CollaborativeProcessDTOPeticion process);

    // Listar procesos (solo Respuesta, porque se devuelven al cliente)
    public List<CollaborativeProcessDTORespuesta> getAllProcesses();

    // Obtener un proceso por su ID
    public CollaborativeProcessDTORespuesta getProcessById(Integer idProcess);
    
    // Eliminar proceso (soft delete o lógica que definas)
    public boolean deleteProcess(Integer idProcess);

    // Modificar proceso existente (entrada = Petición, salida = Respuesta completa)
    public CollaborativeProcessDTORespuesta updateProcess(Integer idProcess, CollaborativeProcessDTOPeticion updatedProcess);

    //Recuperar toda la informacion asociada
    co.edu.unicauca.child_programming_backend.DTO.output.full.ProcessFullDTORespuesta 
        getFullProcess(Integer idProcess);

}
