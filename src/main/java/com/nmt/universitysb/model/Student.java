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
@Table(name = "student")
public class Student implements Serializable {
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
    @Column(name = "gender")
    private boolean gender;
    @NotEmpty(message = "Căn cước không được để trống")
    @Column(name = "identification")
    private String identification;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @NotBlank(message = "Số điện thoại không được để trống")
    @Column(name = "phone")
    private String phone;
    @NotEmpty(message = "Địa chỉ không được để trống")
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "classes_id", referencedColumnName = "id", columnDefinition = "VARCHAR(8)")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Classes classesId;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id", columnDefinition = "VARCHAR(10)")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty facultyId;
    @JoinColumn(name = "major_id", referencedColumnName = "id", columnDefinition = "VARCHAR(12)")
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Major majorId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<StudentSubject> studentSubjectSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<TuitionFee> tuitionFeesSet;

    @Override
    public int hashCode() {
        // Bạn có thể tạm thời tắt phương thức hashCode để kiểm tra xem lỗi có tiếp tục hay không.
        // return Objects.hash(id, name, birthday, gender, identification, phone, address);
        return super.hashCode();
    }

}
