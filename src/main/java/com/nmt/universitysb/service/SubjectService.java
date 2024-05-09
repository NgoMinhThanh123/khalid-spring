package com.nmt.universitysb.service;
import com.nmt.universitysb.dto.SubjectDto;
import com.nmt.universitysb.dto.TuitionFeeAndSubjectDto;
import com.nmt.universitysb.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<Subject> findAll();
    Page<Subject> findAll(Pageable pageable);
    Optional<Subject> findById(String id);
    Page<Subject> findAllByNameContaining(String keyword, Pageable pageable);
    Subject save(Subject f);
    boolean deleteSubject(String id);
    List<SubjectDto> getSubjectByLecturerId(String lecturerId);
    List<SubjectDto> getSubjectBySemesterId(String semesterId);
    List<SubjectDto> getSubjectByStudentId(String studentId);
    List<SubjectDto> getSubjectByMajorId(String majorId, String semesterId);
    List<SubjectDto> getSubjectByStudentAndSemesterId(String studentId, String semesterId);
    List<SubjectDto> getSubjectTemporaryCourse(String studentId, String semesterId);
    List<SubjectDto> getSubjectByEducationProgram(String studentId, String semesterId, String majorId);
    List<TuitionFeeAndSubjectDto> getTuitionFeeOfSemester(String studentId, String semesterId);
}
