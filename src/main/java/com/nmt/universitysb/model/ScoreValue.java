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
@Table(name = "score_value")
public class ScoreValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "value")
    private double value;
    @JoinColumn(name = "score_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Score scoreId;
    @JoinColumn(name = "score_column_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private ScoreColumn scoreColumnId;
}
