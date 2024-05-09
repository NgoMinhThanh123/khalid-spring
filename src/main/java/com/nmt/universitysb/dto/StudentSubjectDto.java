package com.nmt.universitysb.dto;

import com.nmt.universitysb.model.Student;
import com.nmt.universitysb.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentSubjectDto {
    private int id;
    private Boolean status;
    private Student studentId;
    private Subject subjectId;
}
