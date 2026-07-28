package co.edu.unicauca.child_programming_backend.domain.models;

import co.edu.unicauca.child_programming_backend.domain.enums.PracticeType;
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
@Table(name = "practice")
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_practice")
    private Integer id_practice;

    @Column(name = "name_practice", nullable = false, length = 150, unique = true)
    private String name_practice;

    @Column(name = "description_practice", length = 250)
    private String description_practice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PracticeType type_practice;
}

