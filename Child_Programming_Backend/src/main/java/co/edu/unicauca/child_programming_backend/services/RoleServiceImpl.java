package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.RoleDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.RoleDTORespuesta;
import co.edu.unicauca.child_programming_backend.domain.models.ChildActivity;
import co.edu.unicauca.child_programming_backend.domain.models.Role;
import co.edu.unicauca.child_programming_backend.domain.repositories.ChildActivityRepository;
import co.edu.unicauca.child_programming_backend.domain.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ChildActivityRepository childActivityRepository;

    @Override
    public RoleDTORespuesta createRole(RoleDTOPeticion request) {
        Role role = new Role();
        role.setName_role(request.getName_role());
        role.setDescription_role(request.getDescription_role());
        role.setSkills_role(request.getSkills_role());
        
        Role newRole = roleRepository.save(role);
        return convertirADTO(newRole);
    }

    @Override
    public RoleDTORespuesta updateRole(Integer id, RoleDTOPeticion request) {
        return roleRepository.findById(id).map(role -> {
            role.setName_role(request.getName_role());
            role.setDescription_role(request.getDescription_role());
            role.setSkills_role(request.getSkills_role());
            Role updatedRole = roleRepository.save(role);
            return convertirADTO(updatedRole);
        }).orElse(null);
    }

    @Override
    public boolean deleteRole(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<RoleDTORespuesta> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTORespuesta getRoleById(Integer id) {
        return roleRepository.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    @Override
    public RoleDTORespuesta assignRoleToActivity(Integer idRole, Integer idActivity) {
        Role role = roleRepository.findById(idRole)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + idRole));

        ChildActivity activity = childActivityRepository.findById(idActivity)
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada con id: " + idActivity));

        // Evitar duplicados
        if (!activity.getRoles().contains(role)) {
            activity.getRoles().add(role);
            childActivityRepository.save(activity);
        }

        return convertirADTO(role);
    }

    @Override
    public RoleDTORespuesta unassignRoleFromActivity(Integer idRole, Integer idActivity) {
        // Buscar el rol
        Role role = roleRepository.findById(idRole)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + idRole));

        // Buscar la actividad
        ChildActivity activity = childActivityRepository.findById(idActivity)
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada con id: " + idActivity));

        // Si está asignado, lo removemos
        if (activity.getRoles().contains(role)) {
            activity.getRoles().remove(role);
            childActivityRepository.save(activity);
        } else {
            throw new RuntimeException("El rol no está asignado a esta actividad.");
        }

        return convertirADTO(role);
    }


    // Método privado para mapear entidad -> DTO
    private RoleDTORespuesta convertirADTO(Role role) {
        return new RoleDTORespuesta(
                role.getId_role(),
                role.getName_role(),
                role.getDescription_role(),
                role.getSkills_role()
        );
    }
}
