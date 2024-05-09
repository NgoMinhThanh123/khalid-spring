package com.nmt.universitysb.service;

import com.nmt.universitysb.dto.StudentSubjectDto;
import com.nmt.universitysb.model.StudentSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentSubjectService{
    List<StudentSubject> findAll();
    Page<StudentSubject> findAll(Pageable pageable);
    Optional<StudentSubject> findById(int id);
    Page<StudentSubject> findAllByStudentIdContaining(String keyword, Pageable pageable);
    StudentSubject save(StudentSubject f);
    List<StudentSubjectDto> courseRegister(List<Map<String, String>> paramsList);
    List<StudentSubjectDto> temporaryCourseRegister(List<Map<String, String>> paramsList);
    boolean deleteStudentSubject(int id);
    Optional<StudentSubject> getStudentSubjectByStudentAndSubjectId(String studentId, String subjectId);
    List<StudentSubjectDto> getTemporaryCourse(String studentId, String semesterId);
    List<StudentSubjectDto> getAlreadyCourse(String studentId, String semesterId);
}
