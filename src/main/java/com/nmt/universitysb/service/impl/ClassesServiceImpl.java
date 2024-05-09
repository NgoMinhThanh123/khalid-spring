package com.nmt.universitysb.service.impl;
import com.nmt.universitysb.model.Classes;
import com.nmt.universitysb.repository.ClassesRepository;
import com.nmt.universitysb.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    private ClassesRepository classesRepo;

    @Override
    public List<Classes> findAll() {
        return this.classesRepo.findAll();
    }

    @Override
    public Page<Classes> findAll(Pageable pageable) {
        return this.classesRepo.findAll(pageable);
    }

    @Override
    public Optional<Classes> findById(String id) {
        return this.classesRepo.findById(id);
    }

    @Override
    public Page<Classes> findAllByIdContaining(String keyword, Pageable pageable) {
        return this.classesRepo.findAllByIdContaining(keyword, pageable);
    }

    @Override
    public Classes save(Classes f) {
        return this.classesRepo.save(f);
    }

    @Override
    public long count() {
        return this.classesRepo.count();
    }

    @Override
    public boolean deleteClasse(String id) {
        this.classesRepo.deleteById(id);
        return true;
    }
}
