package co.edu.unicauca.child_programming_backend.domain.models;

//import java.util.List;
import java.util.Set;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "child_activity")
// Definimos estrategia de herencia
@Inheritance(strategy = InheritanceType.JOINED)
public class ChildActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activity")
    private Integer id_activity;

    @Column(name = "name_activity", nullable = false, length = 150)
    private String name_activity;

    @Column(name = "description_activity", length = 250)
    private String description_activity;

    // Relación con Practice
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_practice", nullable = true)
    private Practice practice;

    // Relación con Thinklet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_thinklet", nullable = true)
    private Thinklet thinklet;

    // Relación ManyToMany con Role
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "role_child_activity",
        joinColumns = @JoinColumn(name = "id_activity"),
        inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles;
    /*
     * Set no permite elementos duplicados, mientras que List sí.
     * Ejemplo: si tienes un usuario, no tiene sentido que tenga el rol ADMIN dos veces.
     * Al usar Set, te aseguras automáticamente de que cada rol sea único para ese usuario.
     */
    /*@JsonIgnore
    private List<Role> roles; */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_process", nullable = false,
                foreignKey = @ForeignKey(name = "fk_activity_process"))
    private CollaborativeProcess process;

    // Relación hacia Round
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_round", nullable = true)
    private Round parentRound;

}

