package com.nmt.universitysb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "Title không được để trống")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "Content không được để trống")
    @Column(name = "content")
    private String content;
    @Column(name = "post_time")
    @Temporal(TemporalType.DATE)
    private Date postTime;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Set<Comment> commentSet;
}
