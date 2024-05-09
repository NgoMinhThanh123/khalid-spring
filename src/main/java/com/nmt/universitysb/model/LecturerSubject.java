package com.nmt.universitysb.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecturer_subject")
public class LecturerSubject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", columnDefinition = "VARCHAR(10)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Lecturer lecturerId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id", columnDefinition = "VARCHAR(10)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Subject subjectId;
}
