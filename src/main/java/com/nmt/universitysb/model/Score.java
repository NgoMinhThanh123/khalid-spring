package com.nmt.universitysb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "score")
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "semester_id", referencedColumnName = "id", columnDefinition = "VARCHAR(5)")
    @ManyToOne(optional = false)
    private Semester semesterId;
    @JoinColumn(name = "student_subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private StudentSubject studentSubjectId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scoreId")
    @JsonIgnore
    private Set<ScoreValue> scoreValueSet;

}
