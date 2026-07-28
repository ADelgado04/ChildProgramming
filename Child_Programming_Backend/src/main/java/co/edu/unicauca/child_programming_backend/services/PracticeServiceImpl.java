package co.edu.unicauca.child_programming_backend.services;

import co.edu.unicauca.child_programming_backend.DTO.input.PracticeDTOPeticion;
import co.edu.unicauca.child_programming_backend.DTO.output.PracticeDTORespuesta;
import co.edu.unicauca.child_programming_backend.domain.enums.PracticeType;
import co.edu.unicauca.child_programming_backend.domain.models.Practice;
import co.edu.unicauca.child_programming_backend.domain.repositories.PracticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PracticeServiceImpl implements IPracticeService {

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PracticeDTORespuesta createPractice(PracticeDTOPeticion request) {
        Practice practice = new Practice();
        practice.setName_practice(request.getName_practice());
        practice.setDescription_practice(request.getDescription_practice());
        practice.setType_practice(PracticeType.valueOf(request.getType_practice().toUpperCase()));

        Practice saved = practiceRepository.save(practice);
        return modelMapper.map(saved, PracticeDTORespuesta.class);
    }

    @Override
    public PracticeDTORespuesta updatePractice(Integer id, PracticeDTOPeticion request) {
        Optional<Practice> optionalPractice = practiceRepository.findById(id);
        if (optionalPractice.isPresent()) {
            Practice practice = optionalPractice.get();
            practice.setName_practice(request.getName_practice());
            practice.setDescription_practice(request.getDescription_practice());
            practice.setType_practice(PracticeType.valueOf(request.getType_practice().toUpperCase()));

            Practice updated = practiceRepository.save(practice);
            return modelMapper.map(updated, PracticeDTORespuesta.class);
        }
        return null;
    }

    @Override
    public boolean deletePractice(Integer id) {
        if (practiceRepository.existsById(id)) {
            practiceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<PracticeDTORespuesta> getAllPractices() {
        return practiceRepository.findAll()
                .stream()
                .map(practice -> modelMapper.map(practice, PracticeDTORespuesta.class))
                .collect(Collectors.toList());
    }

    @Override
    public PracticeDTORespuesta getPracticeById(Integer id) {
        Optional<Practice> practice = practiceRepository.findById(id);
        return practice.map(value -> modelMapper.map(value, PracticeDTORespuesta.class)).orElse(null);
    }
}
