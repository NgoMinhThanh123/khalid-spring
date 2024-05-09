package com.nmt.universitysb.service.impl;
import com.nmt.universitysb.model.ScoreValue;
import com.nmt.universitysb.repository.ScoreValueRepository;
import com.nmt.universitysb.service.ScoreValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScoreValueServiceImpl implements ScoreValueService {
    @Autowired
    private ScoreValueRepository scoreValueRepo;

    @Override
    public List<ScoreValue> findAll() {
        return this.scoreValueRepo.findAll();
    }

    @Override
    public Page<ScoreValue> findAll(Pageable pageable) {
        return this.scoreValueRepo.findAll(pageable);
    }

    @Override
    public Optional<ScoreValue> findById(int id) {
        return this.scoreValueRepo.findById(id);
    }

    @Override
    public Page<ScoreValue> findAllByIdContaining(String keyword, Pageable pageable) {
        return this.scoreValueRepo.findAllByIdContaining(keyword, pageable);
    }

    @Override
    public ScoreValue save(ScoreValue f) {
        return this.scoreValueRepo.save(f);
    }

    @Override
    public boolean deleteScoreValue(int id) {
        this.scoreValueRepo.deleteById(id);
        return true;
    }
}
