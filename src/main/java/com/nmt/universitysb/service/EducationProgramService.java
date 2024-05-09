package com.nmt.universitysb.service;

import com.nmt.universitysb.model.EducationProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EducationProgramService {
    List<EducationProgram> findAll();
    Page<EducationProgram> findAll(Pageable pageable);
    Optional<EducationProgram> findById(int id);
    Page<EducationProgram> findAllByMajorIdContaining(String keyword, Pageable pageable);
    EducationProgram save(EducationProgram f);
    long count();
    boolean deleteEducationProgram(int id);
}
