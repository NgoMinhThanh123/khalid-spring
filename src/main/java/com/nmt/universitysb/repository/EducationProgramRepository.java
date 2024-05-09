package com.nmt.universitysb.repository;

import com.nmt.universitysb.model.EducationProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationProgramRepository extends JpaRepository<EducationProgram, String> {
    Optional<EducationProgram> findById(int id);

    Page<EducationProgram> findAllByMajorIdContaining(String keyword, Pageable pageable);
    EducationProgram save(EducationProgram f);
}
