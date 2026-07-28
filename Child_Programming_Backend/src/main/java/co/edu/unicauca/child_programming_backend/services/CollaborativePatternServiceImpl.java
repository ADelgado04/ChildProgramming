package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.CollaborativePatternDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.CollaborativePatternDTORespuesta;
import co.edu.unicauca.child_programming_backend.domain.models.CollaborativePattern;
import co.edu.unicauca.child_programming_backend.domain.repositories.CollaborativePatternRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollaborativePatternServiceImpl implements ICollaborativePatternService {

    private final CollaborativePatternRepository patternRepository;

    @Override
    public CollaborativePatternDTORespuesta createPattern(CollaborativePatternDTOPeticion request) {
        CollaborativePattern pattern = new CollaborativePattern();
        pattern.setName_pattern(request.getName_pattern());
        pattern.setDescription_pattern(request.getDescription_pattern());

        CollaborativePattern saved = patternRepository.save(pattern);
        return mapToDTO(saved);
    }

    @Override
    public CollaborativePatternDTORespuesta updatePattern(Integer id, CollaborativePatternDTOPeticion request) {
        CollaborativePattern pattern = patternRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pattern no encontrado"));

        pattern.setName_pattern(request.getName_pattern());
        pattern.setDescription_pattern(request.getDescription_pattern());

        CollaborativePattern updated = patternRepository.save(pattern);
        return mapToDTO(updated);
    }

    @Override
    public boolean deletePattern(Integer id) {
        if (!patternRepository.existsById(id)) {
            throw new RuntimeException("Pattern no encontrado");
        }
        patternRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CollaborativePatternDTORespuesta> getAllPatterns() {
        return patternRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CollaborativePatternDTORespuesta getPatternById(Integer id) {
        return patternRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Pattern no encontrado"));
    }

    private CollaborativePatternDTORespuesta mapToDTO(CollaborativePattern pattern) {
        CollaborativePatternDTORespuesta dto = new CollaborativePatternDTORespuesta();
        dto.setId_pattern(pattern.getId_pattern());
        dto.setName_pattern(pattern.getName_pattern());
        dto.setDescription_pattern(pattern.getDescription_pattern());
        return dto;
    }
}

