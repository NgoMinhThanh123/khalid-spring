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
@Table(name = "credit_price")
public class CreditPrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "price")
    private Double price;
    @JoinColumn(name = "major_id", referencedColumnName = "id", columnDefinition = "VARCHAR(12)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Major majorId;
    @JoinColumn(name = "semester_id", referencedColumnName = "id", columnDefinition = "VARCHAR(5)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Semester semesterId;
}
