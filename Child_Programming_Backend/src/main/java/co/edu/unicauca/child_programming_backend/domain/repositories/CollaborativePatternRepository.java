package co.edu.unicauca.child_programming_backend.domain.repositories;

import co.edu.unicauca.child_programming_backend.domain.models.CollaborativePattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborativePatternRepository extends JpaRepository<CollaborativePattern, Integer> {
}

