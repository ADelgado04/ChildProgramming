package co.edu.unicauca.child_programming_backend.domain.repositories;

import co.edu.unicauca.child_programming_backend.domain.models.ChildActivity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildActivityRepository extends JpaRepository<ChildActivity, Integer> {
    //Aquí se pueden agregar métodos personalizados si se necesitan consultas específicas
    @Query("SELECT a FROM ChildActivity a WHERE TYPE(a) <> Round")
    List<ChildActivity> findOnlyActivities();
}
