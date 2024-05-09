package com.nmt.universitysb.repository;

import com.nmt.universitysb.model.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MajorRepository extends JpaRepository<Major, String> {
    Optional<Major> findById(String id);
    Page<Major> findAllByNameContaining(String keyword, Pageable pageable);
    Major save(Major f);
    void deleteById(String id);

    @Query("select m " +
            "from Major m " +
            "join Subject s on s.majorId.id = m.id " +
            "where s.id = :subjectId")
    Major getMajorBySubjectId(@Param("subjectId") String subjectId);
}
