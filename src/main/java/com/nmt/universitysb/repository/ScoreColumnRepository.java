package com.nmt.universitysb.repository;

import com.nmt.universitysb.model.ScoreColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreColumnRepository extends JpaRepository<ScoreColumn, Integer> {
    Optional<ScoreColumn> findById(int id);
    Page<ScoreColumn> findAllByNameContaining(String keyword, Pageable pageable);
    ScoreColumn save(ScoreColumn f);
    void deleteById(int id);
    @Query(value ="SELECT sc.* \n"
            + "FROM score_column sc \n"
            + "where sc.name = :scoreColumnName",nativeQuery = true)
    ScoreColumn getScoreColumnByName(@Param("scoreColumnName") String scoreColumnName);
}
