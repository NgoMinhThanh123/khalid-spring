package com.nmt.universitysb.service.impl;

import com.nmt.universitysb.model.ScorePercent;
import com.nmt.universitysb.repository.ScorePercentRepository;
import com.nmt.universitysb.service.ScorePercentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScorePercentServiceImpl implements ScorePercentService {
    @Autowired
    private ScorePercentRepository scorePercentRepository;

    @Override
    public List<ScorePercent> findAll() {
        return this.scorePercentRepository.findAll();
    }

    @Override
    public Page<ScorePercent> findAll(Pageable pageable) {
        return this.scorePercentRepository.findAll(pageable);
    }

    @Override
    public Optional<ScorePercent> findById(String id) {
        return this.scorePercentRepository.findById(id);
    }

    @Override
    public Page<ScorePercent> findAllBySubjectIdContaining(String keyword, Pageable pageable) {
        return this.scorePercentRepository.findAllBySubjectIdContaining(keyword, pageable);
    }

    @Override
    public ScorePercent save(ScorePercent f) {
        return this.scorePercentRepository.save(f);
    }

    @Override
    public long count() {
        return this.scorePercentRepository.count();
    }

    @Override
    public boolean deleteScorePercent(String id) {
        this.scorePercentRepository.deleteById(id);
        return true;
    }
}
