package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.ThinkletDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.CollaborativePatternDTORespuesta;
import co.edu.unicauca.child_programming_backend.DTO.output.ThinkletDTORespuesta;
import co.edu.unicauca.child_programming_backend.domain.models.CollaborativePattern;
import co.edu.unicauca.child_programming_backend.domain.models.Thinklet;
import co.edu.unicauca.child_programming_backend.domain.repositories.CollaborativePatternRepository;
import co.edu.unicauca.child_programming_backend.domain.repositories.ThinkletRepository;
import co.edu.unicauca.child_programming_backend.exceptionControllers.exceptions.EntidadNoExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThinkletServiceImpl implements IThinkletService {

    private final ThinkletRepository thinkletRepository;
    private final CollaborativePatternRepository patternRepository;

    @Override
    public ThinkletDTORespuesta createThinklet(ThinkletDTOPeticion request) {
        // Validar que el id_collaborative_pattern venga en la petición
        if (request.getId_pattern() == null) {
            throw new IllegalArgumentException("Debe asignar un patrón colaborativo al Thinklet.");
        }

        Thinklet thinklet = new Thinklet();
        thinklet.setName_thinklet(request.getName_thinklet());
        thinklet.setDescription_thinklet(request.getDescription_thinklet());

        // Buscar y asignar el patrón colaborativo obligatorio
        CollaborativePattern pattern = patternRepository.findById(request.getId_pattern())
                .orElseThrow(() -> new EntidadNoExisteException(
                        "El patrón colaborativo con id " + request.getId_pattern() + " no existe."));

        thinklet.setCollaborativePattern(pattern);

        Thinklet saved = thinkletRepository.save(thinklet);
        return mapToDTO(saved);
    }

    @Override
    public ThinkletDTORespuesta updateThinklet(Integer id, ThinkletDTOPeticion request) {
        Thinklet thinklet = thinkletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thinklet no encontrado"));

        thinklet.setName_thinklet(request.getName_thinklet());
        thinklet.setDescription_thinklet(request.getDescription_thinklet());

        CollaborativePattern pattern = patternRepository.findById(request.getId_pattern())
                .orElseThrow(() -> new RuntimeException("CollaborativePattern no encontrado"));
        thinklet.setCollaborativePattern(pattern);

        Thinklet updated = thinkletRepository.save(thinklet);
        return mapToDTO(updated);
    }

    @Override
    public boolean deleteThinklet(Integer id) {
        if (!thinkletRepository.existsById(id)) {
            throw new RuntimeException("Thinklet no encontrado");
        }
        thinkletRepository.deleteById(id);
        return true;
    }

    /*@Override
    public List<ThinkletDTORespuesta> getAllThinklets() {
        return thinkletRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }*/
    @Override
    @Transactional(readOnly = true)
    public List<ThinkletDTORespuesta> getAllThinklets() {
        Iterable<Thinklet> thinklets = thinkletRepository.findAll();
        List<ThinkletDTORespuesta> thinkletDTOs = new ArrayList<>();

        for (Thinklet thinklet : thinklets) {
            ThinkletDTORespuesta dto = new ThinkletDTORespuesta();
            dto.setId_thinklet(thinklet.getId_thinklet());
            dto.setName_thinklet(thinklet.getName_thinklet());
            dto.setDescription_thinklet(thinklet.getDescription_thinklet());

            // 🔹 Mapear el patrón colaborativo asociado (si existe)
            if (thinklet.getCollaborativePattern() != null) {
                CollaborativePattern pattern = thinklet.getCollaborativePattern();

                CollaborativePatternDTORespuesta patternDTO = new CollaborativePatternDTORespuesta(
                    pattern.getId_pattern(),
                    pattern.getName_pattern(),
                    pattern.getDescription_pattern()
                );

                dto.setPattern(patternDTO);
            }

            thinkletDTOs.add(dto);
        }

        return thinkletDTOs;
    }


    @Override
    public ThinkletDTORespuesta getThinkletById(Integer id) {
        return thinkletRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Thinklet no encontrado"));
    }

    private ThinkletDTORespuesta mapToDTO(Thinklet thinklet) {
        ThinkletDTORespuesta dto = new ThinkletDTORespuesta();
        dto.setId_thinklet(thinklet.getId_thinklet());
        dto.setName_thinklet(thinklet.getName_thinklet());
        dto.setDescription_thinklet(thinklet.getDescription_thinklet());

        // 🔹 Mapear el patrón colaborativo asociado (si existe)
        if (thinklet.getCollaborativePattern() != null) {
            CollaborativePattern pattern = thinklet.getCollaborativePattern();

            CollaborativePatternDTORespuesta patternDTO = new CollaborativePatternDTORespuesta(
                pattern.getId_pattern(),
                pattern.getName_pattern(),
                pattern.getDescription_pattern()
            );

            dto.setPattern(patternDTO);
        }

        return dto;
    }

}

