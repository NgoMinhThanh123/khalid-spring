package com.nmt.universitysb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecturer")
public class Lecturer implements Serializable {
    @Id
    @NotBlank(message = "Id không được để trống")
    @Column(name = "id", columnDefinition = "VARCHAR(10)")
    private String id;
    @NotEmpty(message = "Tên không được để trống")
    @Column(name = "name")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @Column(name = "gender")
    private short gender;
    @Column(name = "identification")
    private String identification;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @NotBlank(message = "Số điện thoại không được để trống")
    @Column(name = "phone")
    private String phone;
    @NotEmpty(message = "Địa không được để trống")
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @NotBlank(message = "Email không được để trống")
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id", columnDefinition = "VARCHAR(10)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Faculty facultyId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private User userId;
    @JoinColumn(name = "classes_id", referencedColumnName = "id", columnDefinition = "VARCHAR(8)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Classes classesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecturerId")
    @JsonIgnore
    private Set<LecturerSubject> lecturerSubjectSet;
}
