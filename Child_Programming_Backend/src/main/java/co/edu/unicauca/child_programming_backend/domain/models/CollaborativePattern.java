package co.edu.unicauca.child_programming_backend.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "collaborative_pattern")


public class CollaborativePattern {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pattern")
    private Integer id_pattern;

    @Column(name = "name_pattern", nullable = false, length = 150, unique = true)
    private String name_pattern;

    @Column(name = "description_pattern", columnDefinition = "text")
    private String description_pattern;
}
