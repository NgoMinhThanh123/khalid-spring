package com.nmt.universitysb.service.impl;
import com.nmt.universitysb.model.Faculty;
import com.nmt.universitysb.repository.FacultyRepository;
import com.nmt.universitysb.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyRepository facultyRepo;

    @Override
    public List<Faculty> findAll() {
        return this.facultyRepo.findAll();
    }

    @Override
    public Page<Faculty> findAll(Pageable pageable) {
        return this.facultyRepo.findAll(pageable);
    }

    @Override
    public Optional<Faculty> findById(String id) {
        return this.facultyRepo.findById(id);
    }

    @Override
    public Page<Faculty> findAllByNameContaining(String keyword, Pageable pageable) {
        return this.facultyRepo.findAllByNameContaining(keyword, pageable);
    }

    @Override
    public Faculty save(Faculty f) {
        return this.facultyRepo.save(f);
    }

    @Override
    public long count() {
        return this.facultyRepo.count();
    }

    @Override
    public boolean deleteFaculty(String id) {
        this.facultyRepo.deleteById(id);
        return true;
    }
}
