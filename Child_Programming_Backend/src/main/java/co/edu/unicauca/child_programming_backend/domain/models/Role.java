package co.edu.unicauca.child_programming_backend.domain.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id_role;

    @Column(name = "name_role", nullable = false, length = 150, unique = true)
    private String name_role;

    @Column(name = "description_role", length = 250)
    private String description_role;

    @Column(name = "skills_role", length = 150)
    private String skills_role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<ChildActivity> activities;
    /*
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<ChildActivity> activities = new HashSet<>();
     */
}
