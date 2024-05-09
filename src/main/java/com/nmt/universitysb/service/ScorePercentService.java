package com.nmt.universitysb.service;

import com.nmt.universitysb.model.ScorePercent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ScorePercentService {
    List<ScorePercent> findAll();
    Page<ScorePercent> findAll(Pageable pageable);
    Optional<ScorePercent> findById(String id);
    Page<ScorePercent> findAllBySubjectIdContaining(String keyword, Pageable pageable);
    ScorePercent save(ScorePercent f);
    long count();
    boolean deleteScorePercent(String id);
}
