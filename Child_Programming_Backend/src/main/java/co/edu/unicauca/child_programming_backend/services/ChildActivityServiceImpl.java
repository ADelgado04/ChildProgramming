package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.ChildActivityDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.ChildActivityDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.RoleDTORespuesta;
import co.edu.unicauca.child_programming_backend.domain.models.ChildActivity;
import co.edu.unicauca.child_programming_backend.domain.models.CollaborativeProcess;
import co.edu.unicauca.child_programming_backend.domain.models.Practice;
import co.edu.unicauca.child_programming_backend.domain.models.Round;
import co.edu.unicauca.child_programming_backend.domain.models.Thinklet;
import co.edu.unicauca.child_programming_backend.domain.repositories.ChildActivityRepository;
import co.edu.unicauca.child_programming_backend.domain.repositories.CollaborativeProcessRepository;
import co.edu.unicauca.child_programming_backend.domain.repositories.PracticeRepository;
import co.edu.unicauca.child_programming_backend.domain.repositories.RoundRepository;
import co.edu.unicauca.child_programming_backend.domain.repositories.ThinkletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChildActivityServiceImpl implements IChildActivityService {

    private final ChildActivityRepository childActivityRepository;
    private final PracticeRepository practiceRepository;
    private final ThinkletRepository thinkletRepository;
    private final CollaborativeProcessRepository processRepository;
    private final RoundRepository roundRepository;

   @Override
    public ChildActivityDTORespuesta createActivity(ChildActivityDTOPeticion request) {

        if (request.getId_process() == null) {
            throw new IllegalArgumentException("Debe asignar un proceso (id_process) a la actividad.");
        }

        CollaborativeProcess process = processRepository.findById(request.getId_process())
                .orElseThrow(() ->
                        new RuntimeException("El proceso con id " + request.getId_process() + " no existe."));

        ChildActivity activity = new ChildActivity();
        activity.setName_activity(request.getName_activity());
        activity.setDescription_activity(request.getDescription_activity());
        activity.setProcess(process);

        // -----------------------------
        //  Asignar RONDA PADRE (si viene)
        // -----------------------------
        if (request.getParent_round_id() != null) {
            Round parentRound = roundRepository.findById(request.getParent_round_id())
                    .orElseThrow(() ->
                            new RuntimeException("La ronda con id " + request.getParent_round_id() + " no existe.")
                    );

            activity.setParentRound(parentRound);
        }

        // -----------------------------
        // Practice (opcional)
        // -----------------------------
        if (request.getId_practice() != null) {
            Practice practice = practiceRepository.findById(request.getId_practice())
                    .orElseThrow(() ->
                            new RuntimeException("La práctica con id " + request.getId_practice() + " no existe."));
            activity.setPractice(practice);
        }

        // -----------------------------
        // Thinklet (opcional)
        // -----------------------------
        if (request.getId_thinklet() != null) {
            Thinklet thinklet = thinkletRepository.findById(request.getId_thinklet())
                    .orElseThrow(() ->
                            new RuntimeException("El thinklet con id " + request.getId_thinklet() + " no existe."));
            activity.setThinklet(thinklet);
        }

        ChildActivity saved = childActivityRepository.save(activity);
        return mapToDTO(saved);
    }

    @Override
    public ChildActivityDTORespuesta updateActivity(Integer id, ChildActivityDTOPeticion request) {
        ChildActivity activity = childActivityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        activity.setName_activity(request.getName_activity());
        activity.setDescription_activity(request.getDescription_activity());

        if (request.getId_process() != null) {
            CollaborativeProcess process = processRepository.findById(request.getId_process())
                    .orElseThrow(() -> new RuntimeException("Proceso no encontrado"));
            activity.setProcess(process);
        }

        if (request.getId_practice() != null) {
            Practice practice = practiceRepository.findById(request.getId_practice())
                    .orElseThrow(() -> new RuntimeException("Práctica no encontrada"));
            activity.setPractice(practice);
        }

        if (request.getId_thinklet() != null) {
            Thinklet thinklet = thinkletRepository.findById(request.getId_thinklet())
                    .orElseThrow(() -> new RuntimeException("Thinklet no encontrado"));
            activity.setThinklet(thinklet);
        }

        // Actualizar parent_round_id
        if (request.getParent_round_id() != null) {
            // Asignar nueva ronda padre
            Round parentRound = roundRepository.findById(request.getParent_round_id())
                    .orElseThrow(() -> new RuntimeException("Ronda padre no encontrada"));

            activity.setParentRound(parentRound);
        } else {
            // Si viene null explícito → quitar relación
            activity.setParentRound(null);
        }

        ChildActivity updated = childActivityRepository.save(activity);
        return mapToDTO(updated);
    }

    @Override
    public boolean deleteActivity(Integer id) {
        if (!childActivityRepository.existsById(id)) {
            throw new RuntimeException("Activity not found");
        }
        childActivityRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ChildActivityDTORespuesta> getAllActivities() {
        return childActivityRepository.findOnlyActivities()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChildActivityDTORespuesta getActivityById(Integer id) {
        return childActivityRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
    }

    @Override
    public ChildActivityDTORespuesta getActivityWithRoles(Integer idActivity) {

        ChildActivity activity = childActivityRepository.findById(idActivity)
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada con id: " + idActivity));

        ChildActivityDTORespuesta dto = mapToDTO(activity);

        //Convertir roles asignados a DTO
        dto.setAssignedRoles(
            activity.getRoles()
                .stream()
                .map(role -> new RoleDTORespuesta(
                    role.getId_role(),
                    role.getName_role(),
                    role.getDescription_role(),
                    role.getSkills_role()
                ))
                .toList()
        );

        return dto;
    }

    private ChildActivityDTORespuesta mapToDTO(ChildActivity activity) {
        ChildActivityDTORespuesta dto = new ChildActivityDTORespuesta();
        dto.setId_activity(activity.getId_activity());
        dto.setName_activity(activity.getName_activity());
        dto.setDescription_activity(activity.getDescription_activity());

        if (activity.getProcess() != null) {
            dto.setId_process(activity.getProcess().getId_process());
            dto.setName_process(activity.getProcess().getName_process());
        }

        if (activity.getPractice() != null) {
            dto.setId_practice(activity.getPractice().getId_practice());
            dto.setName_practice(activity.getPractice().getName_practice());
        }

        if (activity.getThinklet() != null) {
            dto.setId_thinklet(activity.getThinklet().getId_thinklet());
            dto.setName_thinklet(activity.getThinklet().getName_thinklet());
        }
        if (activity.getParentRound() != null) {
            dto.setParent_round_id(activity.getParentRound().getId_activity());
            dto.setParent_round_name(activity.getParentRound().getName_activity());
        }

        return dto;
    }

}
