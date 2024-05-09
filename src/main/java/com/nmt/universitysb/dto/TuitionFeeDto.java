package com.nmt.universitysb.dto;

import com.nmt.universitysb.model.Semester;
import com.nmt.universitysb.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TuitionFeeDto {
    private Integer id;
    private Double tuitionFee;
    private Boolean done;
    private Date dateCreated;
    private Semester semesterId;
    private String studentId;
}
