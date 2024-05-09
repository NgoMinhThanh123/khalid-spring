package com.nmt.universitysb.service;

import com.nmt.universitysb.model.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface SemesterService {
    List<Semester> findAll();
    Page<Semester> findAll(Pageable pageable);
    Optional<Semester> findById(String id);
    Page<Semester> findAllBySchoolYearContaining(String keyword, Pageable pageable);
    Semester save(Semester f);
    boolean deleteSemester(String id);
    List<Semester> getSemesterByLecturerId(String lecturerId);
    List<Semester> getSemesterByStudentId(String studentId);
    List<Semester> getSemesterByLecturerIdAndSubjectId(String lecturerId, String subjectId);
    Semester getLatestSemester();
}
