package co.edu.unicauca.child_programming_backend.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.child_programming_backend.domain.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
