package co.edu.unicauca.child_programming_backend.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.child_programming_backend.domain.models.CollaborativePattern;
import co.edu.unicauca.child_programming_backend.domain.models.CollaborativeProcess;
import co.edu.unicauca.child_programming_backend.domain.models.Round;
import co.edu.unicauca.child_programming_backend.domain.models.Thinklet;
import co.edu.unicauca.child_programming_backend.domain.repositories.CollaborativeProcessRepository;
import co.edu.unicauca.child_programming_backend.DTO.input.CollaborativeProcessDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.CollaborativePatternDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.CollaborativeProcessDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.PracticeDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.RoleDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.RoundDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.ThinkletDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.full.ChildActivityFullDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.full.ProcessFullDTORespuesta;
import co.edu.unicauca.child_programming_backend.exceptionControllers.exceptions.EntidadNoExisteException;

@Service
public class CollaborativeProcessServiceImpl implements ICollaborativeProcessService {

    @Autowired
    private CollaborativeProcessRepository processRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CollaborativeProcessDTORespuesta updateProcess(Integer idProcess, CollaborativeProcessDTOPeticion updatedProcess) {
        CollaborativeProcess existingProcess = processRepository.findById(idProcess)
            .orElseThrow(() -> new EntidadNoExisteException("El proceso con ID " + idProcess + " no existe."));

        // Actualizar solo los campos no nulos del DTO
        if (updatedProcess.getName_process() != null) {
            existingProcess.setName_process(updatedProcess.getName_process());
        }
        if (updatedProcess.getDescription_process() != null) {
            existingProcess.setDescription_process(updatedProcess.getDescription_process());
        }
        if (updatedProcess.getVersion_process() != null) {
            existingProcess.setVersion_process(updatedProcess.getVersion_process());
        }
        if (updatedProcess.getImage() != null) {
            existingProcess.setImage(updatedProcess.getImage());
        }

        CollaborativeProcess updatedEntity = processRepository.save(existingProcess);
        return modelMapper.map(updatedEntity, CollaborativeProcessDTORespuesta.class);
    }

    @Override
    @Transactional
    public CollaborativeProcessDTORespuesta createProcess(CollaborativeProcessDTOPeticion process) {

        CollaborativeProcess objProcess = new CollaborativeProcess();
        objProcess.setName_process(process.getName_process());
        objProcess.setDescription_process(process.getDescription_process());
        objProcess.setVersion_process(process.getVersion_process());
        objProcess.setImage(process.getImage()); // 🔹 Guarda el nombre o ruta del archivo

        CollaborativeProcess savedEntity = processRepository.save(objProcess);

        // 🔹 Mapeo manual para mayor claridad y control
        CollaborativeProcessDTORespuesta dto = new CollaborativeProcessDTORespuesta();
        dto.setId_process(savedEntity.getId_process());
        dto.setName_process(savedEntity.getName_process());
        dto.setDescription_process(savedEntity.getDescription_process());
        dto.setVersion_process(savedEntity.getVersion_process());
        dto.setImage(savedEntity.getImage());

        return dto;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CollaborativeProcessDTORespuesta> getAllProcesses() {
        Iterable<CollaborativeProcess> processes = this.processRepository.findAll();

        List<CollaborativeProcessDTORespuesta> processDTO = new ArrayList<>();

        for (CollaborativeProcess process : processes) {
            CollaborativeProcessDTORespuesta dto = new CollaborativeProcessDTORespuesta();
            dto.setId_process(process.getId_process());
            dto.setName_process(process.getName_process());
            dto.setDescription_process(process.getDescription_process());
            dto.setVersion_process(process.getVersion_process());
            dto.setImage(process.getImage());
            processDTO.add(dto);
        }

        return processDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public CollaborativeProcessDTORespuesta getProcessById(Integer idProcess) {
        CollaborativeProcess process = processRepository.findById(idProcess)
            .orElseThrow(() -> new EntidadNoExisteException("El proceso con ID " + idProcess + " no existe."));

        // Mapeo manual a DTO (siguiendo el estilo de tus otros métodos)
        CollaborativeProcessDTORespuesta dto = new CollaborativeProcessDTORespuesta();
        dto.setId_process(process.getId_process());
        dto.setName_process(process.getName_process());
        dto.setDescription_process(process.getDescription_process());
        dto.setVersion_process(process.getVersion_process());
        dto.setImage(process.getImage());

        return dto;
    }

    @Override
    @Transactional
    public boolean deleteProcess(Integer idProcess) {
        System.out.println("Eliminando proceso: "+ idProcess);
        if (idProcess != null){
            final Boolean bandera = this.processRepository.existsById(idProcess);
            if(!bandera){
                throw new EntidadNoExisteException(
                    "Proceso con id " + idProcess + " no existe en la BD");
            }
        }
        this.processRepository.deleteById(idProcess);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public ProcessFullDTORespuesta getFullProcess(Integer idProcess) {

        CollaborativeProcess process = processRepository.findProcessFull(idProcess);

        if (process == null) {
            throw new EntidadNoExisteException("Proceso con ID " + idProcess + " no existe.");
        }

        // ==========================================================
        // ELIMINAR DUPLICADOS POR FETCH JOIN
        // ==========================================================

        // Deduplicar actividades (por cada rol se duplican)
        process.setActivities(
            new java.util.ArrayList<>(
                new java.util.LinkedHashSet<>(process.getActivities())
            )
        );

        // Deduplicar roles dentro de cada actividad (seguridad)
        process.getActivities().forEach(a -> {
            if (a.getRoles() != null) {
                a.setRoles(
                    new java.util.LinkedHashSet<>(a.getRoles())
                );
            }
        });

        // ==========================================================
        //  MAPEO A DTO
        // ==========================================================

        ProcessFullDTORespuesta dto = new ProcessFullDTORespuesta();

        // Datos del proceso
        dto.setId_process(process.getId_process());
        dto.setName_process(process.getName_process());
        dto.setDescription_process(process.getDescription_process());
        dto.setVersion_process(process.getVersion_process());
        dto.setImage(process.getImage());

        // ==========================================================
        // RONDAS
        // ==========================================================
        dto.setRounds(
            process.getActivities()
                .stream()
                .filter(a -> a instanceof Round)
                .map(a -> {
                    Round r = (Round) a;
                    RoundDTORespuesta rDto = new RoundDTORespuesta();
                    rDto.setId_activity(r.getId_activity());
                    rDto.setName_activity(r.getName_activity());
                    rDto.setDescription_activity(r.getDescription_activity());
                    rDto.setRound_status(r.getRoundStatus());
                    return rDto;
                }).toList()
        );

        // ==========================================================
        // ACTIVIDADES NORMALES
        // ==========================================================
        dto.setActivities(
            process.getActivities()
                .stream()
                .filter(a -> !(a instanceof Round))
                .map(a -> {

                    ChildActivityFullDTORespuesta aDto = new ChildActivityFullDTORespuesta();
                    aDto.setId_activity(a.getId_activity());
                    aDto.setName_activity(a.getName_activity());
                    aDto.setDescription_activity(a.getDescription_activity());

                    // =====================================================
                    // 🔹 MAPEAR RONDA PADRE (LO QUE FALTABA)
                    // =====================================================
                    if (a.getParentRound() != null) {
                        aDto.setParent_round_id(a.getParentRound().getId_activity());
                        aDto.setParent_round_name(a.getParentRound().getName_activity());
                    } else {
                        aDto.setParent_round_id(null);
                        aDto.setParent_round_name(null);
                    }

                    // PRACTICE
                    if (a.getPractice() != null) {
                        PracticeDTORespuesta pDto = new PracticeDTORespuesta();
                        pDto.setId_practice(a.getPractice().getId_practice());
                        pDto.setName_practice(a.getPractice().getName_practice());
                        pDto.setDescription_practice(a.getPractice().getDescription_practice());
                        pDto.setType_practice(a.getPractice().getType_practice().name());
                        aDto.setPractice(pDto);
                    }

                    // THINKLET
                    if (a.getThinklet() != null) {

                        Thinklet t = a.getThinklet();
                        ThinkletDTORespuesta tDto = new ThinkletDTORespuesta();
                        tDto.setId_thinklet(t.getId_thinklet());
                        tDto.setName_thinklet(t.getName_thinklet());
                        tDto.setDescription_thinklet(t.getDescription_thinklet());

                        // PATTERN
                        if (t.getCollaborativePattern() != null) {
                            CollaborativePattern patternEntity = t.getCollaborativePattern();

                            CollaborativePatternDTORespuesta patDto = new CollaborativePatternDTORespuesta();
                            patDto.setId_pattern(patternEntity.getId_pattern());
                            patDto.setName_pattern(patternEntity.getName_pattern());
                            patDto.setDescription_pattern(patternEntity.getDescription_pattern());

                            tDto.setPattern(patDto);
                        }

                        aDto.setThinklet(tDto);
                    }

                    // ROLES
                    if (a.getRoles() != null) {
                        aDto.setAssignedRoles(
                            a.getRoles()
                                .stream()
                                .map(role -> {
                                    RoleDTORespuesta rDto = new RoleDTORespuesta();
                                    rDto.setId_role(role.getId_role());
                                    rDto.setName_role(role.getName_role());
                                    rDto.setDescription_role(role.getDescription_role());
                                    rDto.setSkills_role(role.getSkills_role());
                                    return rDto;
                                }).toList()
                        );
                    }

                    return aDto;
                }).toList()
        );

        return dto;
    }

}
