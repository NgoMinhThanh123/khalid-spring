package com.nmt.universitysb.repository;
import com.nmt.universitysb.model.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String> {

    Optional<Faculty> findById(String id);

    Page<Faculty> findAllByNameContaining(String keyword, Pageable pageable);
    Faculty save(Faculty f);
    void deleteById(String id);

}
