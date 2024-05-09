package com.nmt.universitysb.repository;
import com.nmt.universitysb.model.ScoreValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreValueRepository extends JpaRepository<ScoreValue, Integer> {
    Optional<ScoreValue> findById(int id);
    Page<ScoreValue> findAllByIdContaining(String keyword, Pageable pageable);
    ScoreValue save(ScoreValue f);
    void deleteById(int id);
}
