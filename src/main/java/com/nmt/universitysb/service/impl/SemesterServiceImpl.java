package com.nmt.universitysb.service.impl;

import com.nmt.universitysb.model.Semester;
import com.nmt.universitysb.repository.SemesterRepository;
import com.nmt.universitysb.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesRepo;

    @Override
    public List<Semester> findAll() {
        return  this.semesRepo.findAll();
    }

    @Override
    public Page<Semester> findAll(Pageable pageable) {
        return this.semesRepo.findAll(pageable);
    }

    @Override
    public Optional<Semester> findById(String id) {
        return this.semesRepo.findById(id);
    }

    @Override
    public Page<Semester> findAllBySchoolYearContaining(String keyword, Pageable pageable) {
        return this.semesRepo.findAllBySchoolYearContaining(keyword, pageable);
    }

    @Override
    public Semester save(Semester f) {
        return this.semesRepo.save(f);
    }

    @Override
    public boolean deleteSemester(String id) {
        this.semesRepo.deleteById(id);
        return true;
    }
    @Override
    public List<Semester> getSemesterByLecturerId(String lecturerId) {
        return this.semesRepo.getSemesterByLecturerId(lecturerId);
    }

    @Override
    public List<Semester> getSemesterByStudentId(String studentId) {
        return this.semesRepo.getSemesterByStudentId(studentId);
    }

    @Override
    public List<Semester> getSemesterByLecturerIdAndSubjectId(String lecturerId, String subjectId) {
        return this.semesRepo.getSemesterByLecturerIdAndSubjectId(lecturerId, subjectId);
    }

    @Override
    public Semester getLatestSemester() {
        return this.semesRepo.getLatestSemester();
    }
}
