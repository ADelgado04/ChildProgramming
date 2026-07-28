package co.edu.unicauca.child_programming_backend.domain.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table (name = "collaborative_process")
public class CollaborativeProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_process")
    private Integer id_process;

    @Column(name = "name_process", nullable = false, length = 150, unique = true)
    private String name_process;

    @Column(name = "description_process", length = 250)
    private String description_process;

    @Column(name = "version_process", length = 50)
    private String version_process;

    @Column(name ="image", length = 512)
    private String image;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ChildActivity> activities;


}