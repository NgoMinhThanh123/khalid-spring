package com.nmt.universitysb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "Username không được để trống")
    @Column(name = "username")
    private String username;
    @NotEmpty(message = "Password không được để trống")
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @NotEmpty(message = "Email không được để trống")
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;
    @Column(name = "avatar")
    private String avatar;
    @Transient
    @JsonIgnore
    private MultipartFile file;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<Lecturer> lecturerSet;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
//    private Set<Post> postSet;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
//    private Set<Comment> commentSet;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userId")
    private Student student;
}
