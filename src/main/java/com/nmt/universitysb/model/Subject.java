package com.nmt.universitysb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    @Id
    @NotBlank(message = "Id không được để trống")
    @Column(name = "id", columnDefinition = "VARCHAR(12)")
    private String id;
    @NotEmpty(message = "Tên không được để trống")
    @Column(name = "name")
    private String name;
    @Column(name = "credit")
    private int credit;
    @JoinColumn(name = "major_id", referencedColumnName = "id", columnDefinition = "VARCHAR(12)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Major majorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<LecturerSubject> lecturerSubjectSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId", fetch = FetchType.LAZY)
    private Set<StudentSubject> studentSubjectSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ScorePercent> scorePercentSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId", fetch = FetchType.LAZY)
    private Set<EducationProgram> educationPrograms;
}
