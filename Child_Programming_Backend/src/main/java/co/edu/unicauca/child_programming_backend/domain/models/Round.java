package co.edu.unicauca.child_programming_backend.domain.models;

import java.util.List;

import co.edu.unicauca.child_programming_backend.domain.enums.RoundStatus;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "round")
@PrimaryKeyJoinColumn(name = "id_activity") // usa la PK del padre como FK en esta tabla
public class Round extends ChildActivity {

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_process", nullable = false,
            foreignKey = @ForeignKey(name = "fk_round_process"))
    private CollaborativeProcess process;*/

    @Enumerated(EnumType.STRING)
    @Column(name = "round_status", nullable = false, length = 30)
    private RoundStatus roundStatus;

    // Siempre es iterativa
    /*@PrePersist
    public void prePersist() {
        setIterative(true);
    }*/
   
    @OneToMany(mappedBy = "parentRound", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ChildActivity> activities;
}

