package com.nmt.universitysb.service.impl;

import com.nmt.universitysb.dto.StudentSubjectDto;
import com.nmt.universitysb.model.*;
import com.nmt.universitysb.repository.*;
import com.nmt.universitysb.service.StudentSubjectService;
import com.nmt.universitysb.service.TuitionFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private SemesterRepository semesterRepo;
    @Autowired
    private ScoreRepository scoreRepo;
    @Autowired
    private TuitionFeeService tuitionFeeService;

    @Override
    public List<StudentSubject> findAll() {
        return this.studentSubjectRepository.findAll();
    }

    @Override
    public Page<StudentSubject> findAll(Pageable pageable) {
        return this.studentSubjectRepository.findAll(pageable);
    }

    @Override
    public Optional<StudentSubject> findById(int id) {
        return this.studentSubjectRepository.findById(id);
    }

    @Override
    public Page<StudentSubject> findAllByStudentIdContaining(String keyword, Pageable pageable) {
        return this.studentSubjectRepository.findAllByStudentIdContaining(keyword, pageable);
    }

    @Override
    public StudentSubject save(StudentSubject f) {
        return this.studentSubjectRepository.save(f);
    }

    @Override
    public List<StudentSubjectDto> courseRegister(List<Map<String, String>> paramsList) {
        List<StudentSubjectDto> studentSubjects = new ArrayList<>();
        Map<String, Double> studentTuitionFees = new HashMap<>(); // Lưu trữ tổng tuitionFee của từng sinh viên

        for (Map<String, String> params : paramsList) {
            Optional<Student> student = this.studentRepo.findById(params.get("studentId"));
            Optional<Subject> subject = this.subjectRepo.findById(params.get("subjectId"));
            Optional<Semester> semester = this.semesterRepo.findById(params.get("semesterId"));

            int schoolYear = semester.get().getSchoolYear();


            StudentSubject studentSubject = new StudentSubject();
            studentSubject.setStudentId(student.get());
            studentSubject.setSubjectId(subject.get());
            studentSubject.setStatus(true);

            Optional<StudentSubject> existingStudentSubject = studentSubjectRepository.getStudentSubjectByStudentAndSubjectId(student.get().getId(), subject.get().getId());
            if (!existingStudentSubject.isPresent()) {
                StudentSubject studentSubject1 = this.studentSubjectRepository.save(studentSubject);

                // Tính toán tuitionFee
                Double tuitionFee = this.tuitionFeeService.calcTuitionFee(subject.get().getId(), schoolYear);

                Score score = new Score();
                score.setStudentSubjectId(studentSubject1);
                score.setSemesterId(semester.get());

                Optional<Score> existingScore = scoreRepo.findByStudentSubjectIdAndSemesterId(
                        studentSubject1.getId(),
                        semester.get().getId()
                );

                if (!existingScore.isPresent()) {
                    Score score1 = this.scoreRepo.save(score);

                    StudentSubjectDto studentSubjectDto = new StudentSubjectDto();
                    studentSubjectDto.setStudentId(student.get());
                    studentSubjectDto.setSubjectId(subject.get());
                    studentSubjectDto.setStatus(true);

                    studentSubjects.add(studentSubjectDto);

                    // Cộng dồn tuitionFee vào tổng của sinh viên
                    studentTuitionFees.put(student.get().getId(), studentTuitionFees.getOrDefault(student.get().getId(), 0.0) + tuitionFee);

                }
            } else {
                StudentSubject studentSubject2 = existingStudentSubject.get();
                studentSubject2.setStatus(true);
                this.studentSubjectRepository.save(studentSubject2);

                // Tính toán tuitionFee
                Double tuitionFee = this.tuitionFeeService.calcTuitionFee(subject.get().getId(), schoolYear);

                Score score = new Score();
                score.setStudentSubjectId(studentSubject2);
                score.setSemesterId(semester.get());

                Optional<Score> existingScore = scoreRepo.findByStudentSubjectIdAndSemesterId(
                        studentSubject2.getId(),
                        semester.get().getId()
                );

                if (!existingScore.isPresent()) {
                    Score score1 = this.scoreRepo.save(score);

                    StudentSubjectDto studentSubjectDto = new StudentSubjectDto();
                    studentSubjectDto.setStudentId(student.get());
                    studentSubjectDto.setSubjectId(subject.get());
                    studentSubjectDto.setStatus(true);

                    studentSubjects.add(studentSubjectDto);

                    // Cộng dồn tuitionFee vào tổng của sinh viên
                    studentTuitionFees.put(student.get().getId(), studentTuitionFees.getOrDefault(student.get().getId(), 0.0) + tuitionFee);

                }

            }
        }

        for (Map.Entry<String, Double> entry : studentTuitionFees.entrySet()) {
            String studentId = entry.getKey();
            Double totalTuitionFee = entry.getValue();

            TuitionFee tuitionFee1 = new TuitionFee();
            tuitionFee1.setTuitionFee(totalTuitionFee);
            tuitionFee1.setDone(false);
//            tuitionFee1.setDateCreated(new Date());

            Optional<Student> student1 = this.studentRepo.findById(studentId);
            if (student1.isPresent()) {
                tuitionFee1.setStudentId(student1.get());
            }
            Optional<Semester> semester1 = this.semesterRepo.findById(paramsList.get(0).get("semesterId"));
            if (semester1.isPresent()) {
                tuitionFee1.setSemesterId(semester1.get());
            }

            this.tuitionFeeService.save(tuitionFee1);
        }


        return studentSubjects;
    }

    @Override
    public List<StudentSubjectDto> temporaryCourseRegister(List<Map<String, String>> paramsList) {
        List<StudentSubjectDto> studentSubjects = new ArrayList<>();

        for (Map<String, String> params : paramsList) {
            Optional<Student> student = this.studentRepo.findById(params.get("studentId"));
            Optional<Subject> subject = this.subjectRepo.findById(params.get("subjectId"));
            Optional<Semester> semester = this.semesterRepo.findById(params.get("semesterId"));

            StudentSubject studentSubject = new StudentSubject();
            studentSubject.setStudentId(student.get());
            studentSubject.setSubjectId(subject.get());
            studentSubject.setStatus(false);

            Optional<StudentSubject> existingStudentSubject = studentSubjectRepository.getStudentSubjectByStudentAndSubjectId(student.get().getId(), subject.get().getId());
            if (!existingStudentSubject.isPresent()) {
                StudentSubject studentSubject1 = this.studentSubjectRepository.save(studentSubject);


                    StudentSubjectDto studentSubjectDto = new StudentSubjectDto();
                    studentSubjectDto.setStudentId(student.get());
                    studentSubjectDto.setSubjectId(subject.get());
                    studentSubjectDto.setStatus(false);

                    studentSubjects.add(studentSubjectDto);

                }
            }
        return studentSubjects;
    }

    @Override
    public boolean deleteStudentSubject(int id) {
        this.studentSubjectRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<StudentSubject> getStudentSubjectByStudentAndSubjectId(String studentId, String subjectId) {
        return this.studentSubjectRepository.getStudentSubjectByStudentAndSubjectId(studentId, subjectId);
    }

    @Override
    public List<StudentSubjectDto> getTemporaryCourse(String studentId, String semesterId) {
        return this.studentSubjectRepository.getTemporaryCourse(studentId, semesterId);
    }

    @Override
    public List<StudentSubjectDto> getAlreadyCourse(String studentId, String semesterId) {
        return this.studentSubjectRepository.getAlreadyCourse(studentId, semesterId);
    }
}
