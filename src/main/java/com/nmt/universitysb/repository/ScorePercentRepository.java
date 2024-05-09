package com.nmt.universitysb.repository;

import com.nmt.universitysb.dto.ScorePercentDto;
import com.nmt.universitysb.model.ScorePercent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ScorePercentRepository extends JpaRepository<ScorePercent, String> {
    Optional<ScorePercent> findById(String id);

    Page<ScorePercent> findAllBySubjectIdContaining(String keyword, Pageable pageable);
    ScorePercent save(ScorePercent f);
    void deleteById(String id);
    @Query("select new com.nmt.universitysb.dto.ScorePercentDto(sp.percentCK, sp.percentGK) \n"
            + "FROM ScorePercent sp \n"
            + "join Subject s on sp.subjectId.id = s.id\n"
            + "where s.id = :subjectId\n")
    ScorePercentDto findAllBySubjectId(String subjectId);
}
