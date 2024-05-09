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
@ToString(exclude = {"subjectSet", "studentSet", "creditPriceSet"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "major")
public class Major implements Serializable {
    @Id
    @NotBlank(message = "Id không được để trống")
    @Column(name = "id", columnDefinition = "VARCHAR(12)")
    private String id;
    @NotEmpty(message = "Tên không được để trống")
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id", columnDefinition = "VARCHAR(10)")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Faculty facultyId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "majorId", fetch = FetchType.LAZY)
    private Set<Subject> subjectSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "majorId", fetch = FetchType.LAZY)
    private Set<Student> studentSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "majorId", fetch = FetchType.LAZY)
    private Set<CreditPrice> creditPriceSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "majorId", fetch = FetchType.LAZY)
    private Set<EducationProgram> educationPrograms;
}
