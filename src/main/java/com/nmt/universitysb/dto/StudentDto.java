package com.nmt.universitysb.dto;

import com.nmt.universitysb.model.Classes;
import com.nmt.universitysb.model.Faculty;
import com.nmt.universitysb.model.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String id;

    private String name;

    private Date birthday;

    private boolean gender;

    private String identification;

    private String phone;

    private String address;

    private Classes classesId;

    private Faculty facultyId;

    private Major majorId;

}
