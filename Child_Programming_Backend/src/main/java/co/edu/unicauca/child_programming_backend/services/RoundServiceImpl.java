package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.RoundDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.ChildActivityDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.RoundDTORespuesta;
import co.edu.unicauca.child_programming_backend.domain.enums.RoundStatus;
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
import co.edu.unicauca.child_programming_backend.exceptionControllers.exceptions.EntidadNoExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoundServiceImpl implements IRoundService {

    private final ChildActivityRepository activityRepository;
    private final RoundRepository roundRepository;
    private final PracticeRepository practiceRepository;
    private final ThinkletRepository thinkletRepository;
    private final CollaborativeProcessRepository processRepository;

    @Override
    public RoundDTORespuesta createRound(RoundDTOPeticion request) {

        // 1) Crear la ronda (que HEREDA de ChildActivity)
        Round round = new Round();
        round.setName_activity(request.getName_activity());
        round.setDescription_activity(request.getDescription_activity());

        // Relaciones opcionales
        Practice practice = null;
        if (request.getId_practice() != null) {
            practice = practiceRepository.findById(request.getId_practice())
                    .orElseThrow(() -> new RuntimeException("Practice no encontrada"));
        }

        Thinklet thinklet = null;
        if (request.getId_thinklet() != null) {
            thinklet = thinkletRepository.findById(request.getId_thinklet())
                    .orElseThrow(() -> new RuntimeException("Thinklet no encontrada"));
        }

        CollaborativeProcess process = processRepository.findById(request.getId_process())
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado"));

        round.setPractice(practice);
        round.setThinklet(thinklet);
        round.setProcess(process);
        round.setRoundStatus(RoundStatus.valueOf(request.getRound_status()));

        // Guardar la ronda
        Round savedRound = roundRepository.save(round);

        // ---------------------------------------------------------
        // 2) Crear automáticamente las 4 actividades hijas
        // ---------------------------------------------------------
        List<ChildActivity> internalActivities = Arrays.stream(RoundStatus.values())
                .map(status -> {
                    ChildActivity a = new ChildActivity();

                    // Nombre con formato: ESTADO - NombreRonda
                    String composedName = status.name() + " - " + savedRound.getName_activity();
                    
                    a.setName_activity(composedName);
                    a.setDescription_activity("Actividad automática: " + status.name());
                    a.setPractice(null);
                    a.setThinklet(null);
                    a.setProcess(process);
                    a.setParentRound(savedRound); // Asignar ronda padre
                    return a;
                })
                .collect(Collectors.toList());

        // Guardar las 4 actividades hijas usando el repo correcto
        internalActivities.forEach(activityRepository::save);

        // Asociar la lista a la ronda
        savedRound.setActivities(internalActivities);

        // ---------------------------------------------------------
        // 3) Retornar DTO completo con subactividades
        // ---------------------------------------------------------
        return mapToDTO(savedRound);
    }

    @Override
    public RoundDTORespuesta updateRound(Integer id, RoundDTOPeticion request) {
        Round round = roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round no encontrado"));

        round.setName_activity(request.getName_activity());
        round.setDescription_activity(request.getDescription_activity());

        // Practice es opcional
        Practice practice = null;
        if (request.getId_practice() != null) {
            practice = practiceRepository.findById(request.getId_practice())
                    .orElseThrow(() -> new RuntimeException("Practice no encontrada"));
        }

        // Thinklet es opcional
        Thinklet thinklet = null;
        if (request.getId_thinklet() != null) {
            thinklet = thinkletRepository.findById(request.getId_thinklet())
                    .orElseThrow(() -> new RuntimeException("Thinklet no encontrada"));
        }

        // Process sí es obligatorio
        CollaborativeProcess process = processRepository.findById(request.getId_process())
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado"));

        // Set de relaciones opcionales
        round.setPractice(practice);
        round.setThinklet(thinklet);
        round.setProcess(process);

        round.setRoundStatus(RoundStatus.valueOf(request.getRound_status()));

        Round updated = roundRepository.save(round);
        return mapToDTO(updated);
    }

    @Override
    public boolean deleteRound(Integer id) {
        if (!roundRepository.existsById(id)) {
            throw new RuntimeException("Round no encontrado");
        }
        roundRepository.deleteById(id);
        return true;
    }

    @Override
    public List<RoundDTORespuesta> getAllRounds() {
        return roundRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoundDTORespuesta getRoundById(Integer id) {
        return roundRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntidadNoExisteException("Ronda no encontrada"));
    }

    @Override
    public List<RoundDTORespuesta> getRoundsByProcess(Integer idProcess) {

        // Verificar que el proceso exista
        if (!processRepository.existsById(idProcess)) {
            throw new EntidadNoExisteException("Proceso con ID " + idProcess + " no existe.");
        }

        return roundRepository.findByProcessId(idProcess)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private RoundDTORespuesta mapToDTO(Round round) {

        RoundDTORespuesta dto = new RoundDTORespuesta();

        dto.setId_activity(round.getId_activity());
        dto.setName_activity(round.getName_activity());
        dto.setDescription_activity(round.getDescription_activity());
        dto.setRound_status(round.getRoundStatus());

        if (round.getProcess() != null) {
            dto.setName_process(round.getProcess().getName_process());
            dto.setId_process(round.getProcess().getId_process());
        }

        if (round.getPractice() != null) {
            dto.setName_practice(round.getPractice().getName_practice());
            dto.setId_practice(round.getPractice().getId_practice());
        }

        if (round.getThinklet() != null) {
            dto.setName_thinklet(round.getThinklet().getName_thinklet());
            dto.setId_thinklet(round.getThinklet().getId_thinklet());
        }

        // ------------------------------------------------------------------
        // Subactividades
        // ------------------------------------------------------------------
        if (round.getActivities() != null) {
            List<ChildActivityDTORespuesta> subs = round.getActivities()
                    .stream()
                    .map(a -> {
                        ChildActivityDTORespuesta res = new ChildActivityDTORespuesta();
                        res.setId_activity(a.getId_activity());
                        res.setName_activity(a.getName_activity());
                        res.setDescription_activity(a.getDescription_activity());
                        res.setId_process(a.getProcess().getId_process());
                        res.setName_process(a.getProcess().getName_process());
                        res.setParent_round_id(round.getId_activity());
                        return res;
                    })
                    .collect(Collectors.toList());

            dto.setSubActivities(subs);
        }

        return dto;
    }
}