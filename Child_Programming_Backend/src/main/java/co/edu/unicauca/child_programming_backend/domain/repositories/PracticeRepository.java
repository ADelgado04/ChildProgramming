package co.edu.unicauca.child_programming_backend.domain.repositories;

import co.edu.unicauca.child_programming_backend.domain.models.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Integer> {
    // Métodos personalizados si necesitas consultas específicas
}

