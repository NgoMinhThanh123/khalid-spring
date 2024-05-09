package com.nmt.universitysb.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Data
@ToString(exclude = {"studentSet", "classesSet"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculty")
public class Faculty implements Serializable {
    @Id
    @NotBlank(message = "Id không được để trống")
    @Column(name = "id", columnDefinition = "VARCHAR(10)")
    private String id;
    @NotEmpty(message = "Tên không được để trống")
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultyId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student> studentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultyId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Classes> classesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultyId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Lecturer> lecturerSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultyId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Major> majorSet;

    @Override
    public String toString() {
        return "Faculty[id=" + this.id + ", name=" + this.name +"]";
    }
}
