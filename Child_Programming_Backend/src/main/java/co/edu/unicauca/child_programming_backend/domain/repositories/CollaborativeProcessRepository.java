package co.edu.unicauca.child_programming_backend.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.child_programming_backend.domain.models.CollaborativeProcess;

public interface CollaborativeProcessRepository extends JpaRepository<CollaborativeProcess, Integer> {

    @Query("""
        SELECT DISTINCT p FROM CollaborativeProcess p
        LEFT JOIN FETCH p.activities a
        LEFT JOIN FETCH a.practice pr
        LEFT JOIN FETCH a.thinklet t
        LEFT JOIN FETCH t.collaborativePattern pat
        LEFT JOIN FETCH a.roles roles
        WHERE p.id_process = :id
    """)
    CollaborativeProcess findProcessFull(@Param("id") Integer id);
}