package com.nmt.universitysb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "score_percent")
public class ScorePercent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "percentCK")
    private Double percentCK;
    @Column(name = "percentGK")
    private Double percentGK;
    @JoinColumn(name = "subject_id", referencedColumnName = "id", columnDefinition = "VARCHAR(12)")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Subject subjectId;
}
