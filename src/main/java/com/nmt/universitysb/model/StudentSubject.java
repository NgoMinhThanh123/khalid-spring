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
@Table(name = "student_subject")
public class StudentSubject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "student_id", referencedColumnName = "id", columnDefinition = "VARCHAR(10)")
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Student studentId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id", columnDefinition = "VARCHAR(12)")
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Subject subjectId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentSubjectId")
    @JsonIgnore
    private Set<Score> scoreSet;

}
