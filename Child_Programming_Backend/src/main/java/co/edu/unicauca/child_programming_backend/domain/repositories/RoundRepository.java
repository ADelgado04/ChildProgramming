package co.edu.unicauca.child_programming_backend.domain.repositories;

import co.edu.unicauca.child_programming_backend.domain.models.Round;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Integer> {
    // Métodos personalizados si necesitas consultas específicas
    @EntityGraph(attributePaths = {"activities"})
    List<Round> findAll();

    @EntityGraph(attributePaths = {"activities"})
    Optional<Round> findById(Integer id);

    @Query("SELECT r FROM Round r WHERE r.process.id_process = :idProcess")
    List<Round> findByProcessId(@Param("idProcess") Integer idProcess);

}
