package com.nmt.universitysb.service;

import com.nmt.universitysb.dto.StuScoreDto;
import com.nmt.universitysb.dto.StudentDto;
import com.nmt.universitysb.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Page<Student> findAll(Pageable pageable);
    Optional<Student> findById(String id);
    Page<Student> findAllByNameContaining(String keyword, Pageable pageable);
    Student save (Student f);
    Student saveAndCreateUser(Student f);
    List<Student> save(List<Student> students);
    long count();
    boolean deleteStudent(String id);
    StudentDto getStudentByUsername(String username);
    StudentDto getListStudentForParents(String studentId, String studentName, Date studentBirthday, String classId, String studentIdentification);
    List<StudentDto> getStudentByHomeroomTeacher(String lecturerId);
    List<StuScoreDto> getListStudent(String lectureId, String classId, String subjectId, String semesterId);
    List<String> getAllMailOfStudent(String lecturerId, String subjectId, String semesterId);

}
