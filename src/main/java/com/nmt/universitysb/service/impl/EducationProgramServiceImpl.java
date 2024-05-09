package com.nmt.universitysb.service.impl;

import com.nmt.universitysb.model.EducationProgram;
import com.nmt.universitysb.repository.EducationProgramRepository;
import com.nmt.universitysb.service.EducationProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationProgramServiceImpl implements EducationProgramService {

    @Autowired
    private EducationProgramRepository educationProgramRepository;

    @Override
    public List<EducationProgram> findAll() {
        return this.educationProgramRepository.findAll();
    }

    @Override
    public Page<EducationProgram> findAll(Pageable pageable) {
        return this.educationProgramRepository.findAll(pageable);
    }

    @Override
    public Optional<EducationProgram> findById(int id) {
        return this.educationProgramRepository.findById(id);
    }

    @Override
    public Page<EducationProgram> findAllByMajorIdContaining(String keyword, Pageable pageable) {
        return this.educationProgramRepository.findAllByMajorIdContaining(keyword, pageable);
    }

    @Override
    public EducationProgram save(EducationProgram f) {
        return this.educationProgramRepository.save(f);
    }

    @Override
    public long count() {
        return this.educationProgramRepository.count();
    }

    @Override
    public boolean deleteEducationProgram(int id) {
        this.educationProgramRepository.deleteById(String.valueOf(id));
        return true;
    }
}
