package com.nmt.universitysb.service;

import com.nmt.universitysb.dto.TuitionFeeDto;
import com.nmt.universitysb.model.TuitionFee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface TuitionFeeService {
    List<TuitionFee> findAll();
    Page<TuitionFee> findAll(Pageable pageable);
    Optional<TuitionFee> findById(int id);
    TuitionFee findByTuitionFeeId(int id);
    Page<TuitionFee> findAllByStudentIdContaining(String keyword, Pageable pageable);
    TuitionFee save(TuitionFee f);
    long count();
    boolean deleteTuitionFee(String id);
    Double calcTuitionFee(String subjectId, int schoolYear);
    List<TuitionFeeDto> getTuitionFeeByStudentId(String studentId);
    List<TuitionFeeDto> getTuitionFeeByStudentIdAndSemesterId(String studentId, String semesterId);
}
