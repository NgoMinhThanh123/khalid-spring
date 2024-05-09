package com.nmt.universitysb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "education_program")
public class EducationProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "subject_id", referencedColumnName = "id", columnDefinition = "VARCHAR(12)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Subject subjectId;
    @JoinColumn(name = "semester_id", referencedColumnName = "id", columnDefinition = "VARCHAR(5)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Semester semesterId;
    @JoinColumn(name = "major_id", referencedColumnName = "id", columnDefinition = "VARCHAR(12)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Major majorId;
}
