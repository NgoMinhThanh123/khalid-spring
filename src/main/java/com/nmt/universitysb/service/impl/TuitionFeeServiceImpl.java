package com.nmt.universitysb.service.impl;

import com.nmt.universitysb.dto.TuitionFeeDto;
import com.nmt.universitysb.dto.creditPriceDto;
import com.nmt.universitysb.model.Major;
import com.nmt.universitysb.model.TuitionFee;
import com.nmt.universitysb.repository.*;
import com.nmt.universitysb.service.TuitionFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TuitionFeeServiceImpl implements TuitionFeeService {
    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CreditPriceRepository creditPriceRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private SemesterRepository semesterRepository;


    @Override
    public List<TuitionFee> findAll() {
        return this.tuitionFeeRepository.findAll();
    }

    @Override
    public Page<TuitionFee> findAll(Pageable pageable) {
        return this.tuitionFeeRepository.findAll(pageable);
    }

    @Override
    public Optional<TuitionFee> findById(int id) {
        return this.tuitionFeeRepository.findById(id);
    }

    @Override
    public TuitionFee findByTuitionFeeId(int id) {
        return this.tuitionFeeRepository.findByTuitionFeeId(id);
    }

    @Override
    public Page<TuitionFee> findAllByStudentIdContaining(String keyword, Pageable pageable) {
        return this.tuitionFeeRepository.findAllByStudentIdContaining(keyword, pageable);
    }

    @Override
    public TuitionFee save(TuitionFee f) {
        return this.tuitionFeeRepository.save(f);
    }

    @Override
    public long count() {
        return this.tuitionFeeRepository.count();
    }

    @Override
    public boolean deleteTuitionFee(String id) {
        this.tuitionFeeRepository.deleteById(id);
        return true;
    }

    @Override
    public Double calcTuitionFee(String subjectId, int schoolYear) {

        long credit = this.subjectRepository.getCreditBySubjectId(subjectId);
        Major major = this.majorRepository.getMajorBySubjectId(subjectId);
        creditPriceDto creditPrice = this.creditPriceRepository.getcreditPriceByMajorIdAndSchoolYear(major.getId(), schoolYear);
        double tuitionFee = credit*creditPrice.getPrice();

        return tuitionFee;
    }

    @Override
    public List<TuitionFeeDto> getTuitionFeeByStudentId(String studentId) {
        return this.tuitionFeeRepository.getTuitionFeeByStudentId(studentId);
    }

    @Override
    public List<TuitionFeeDto> getTuitionFeeByStudentIdAndSemesterId(String studentId, String semesterId) {
        return this.tuitionFeeRepository.getTuitionFeeByStudentIdAndSemesterId(studentId, semesterId);
    }
}
