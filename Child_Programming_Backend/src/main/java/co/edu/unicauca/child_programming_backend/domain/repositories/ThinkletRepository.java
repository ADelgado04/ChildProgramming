package co.edu.unicauca.child_programming_backend.domain.repositories;

import co.edu.unicauca.child_programming_backend.domain.models.Thinklet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThinkletRepository extends JpaRepository<Thinklet, Integer> {
    // Métodos personalizados si necesitas consultas específicas
}
