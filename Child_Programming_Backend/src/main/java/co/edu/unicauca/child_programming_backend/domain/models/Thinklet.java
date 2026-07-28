package co.edu.unicauca.child_programming_backend.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "thinklet")
public class Thinklet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_thinklet")
    private Integer id_thinklet;

    @Column(name = "name_thinklet", nullable = false, length = 150, unique = true)
    private String name_thinklet;

    @Column(name = "description_thinklet", length = 250)
    private String description_thinklet;

    // Relación con CollaborativePattern
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_pattern",
        referencedColumnName = "id_pattern",
        nullable = false
    )
    private CollaborativePattern collaborativePattern;
}
