package com.nmt.universitysb.service.impl;///*

import com.nmt.universitysb.model.Major;
import com.nmt.universitysb.repository.MajorRepository;
import com.nmt.universitysb.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepository majorRepo;

    @Override
    public List<Major> findAll() {
        return this.majorRepo.findAll();
    }

    @Override
    public Page<Major> findAll(Pageable pageable) {
        return this.majorRepo.findAll(pageable);
    }

    @Override
    public Optional<Major> findById(String id) {
        return this.majorRepo.findById(id);
    }

    @Override
    public Page<Major> findAllByNameContaining(String keyword, Pageable pageable) {
        return this.majorRepo.findAllByNameContaining(keyword, pageable);
    }

    @Override
    public Major save(Major f) {
        return this.majorRepo.save(f);
    }

    @Override
    public long count() {
        return this.majorRepo.count();
    }

    @Override
    public boolean deleteMajor(String id) {
        this.majorRepo.deleteById(id);
        return true;
    }

}
