package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.RoleDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.RoleDTORespuesta;

import java.util.List;

public interface IRoleService {
    
    // Crear un nuevo rol
    RoleDTORespuesta createRole(RoleDTOPeticion role);

    // Modificar un rol existente
    RoleDTORespuesta updateRole(Integer id, RoleDTOPeticion role);

    // Eliminar un rol por ID
    boolean deleteRole(Integer id);

    // Listar todos los roles
    List<RoleDTORespuesta> getAllRoles();

    // Buscar un rol por ID
    RoleDTORespuesta getRoleById(Integer id);

    //Asignar rol a actividad
    RoleDTORespuesta assignRoleToActivity(Integer idRole, Integer idActivity);

    //Desasignar rol de actividad
    RoleDTORespuesta unassignRoleFromActivity(Integer idRole, Integer idActivity);
}
