package com.nmt.universitysb.service;
import com.nmt.universitysb.model.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FacultyService {
    List<Faculty> findAll();
    Page<Faculty> findAll(Pageable pageable);
    Optional<Faculty> findById(String id);
    Page<Faculty> findAllByNameContaining(String keyword, Pageable pageable);
    Faculty save(Faculty f);
    long count();
    boolean deleteFaculty(String id);

}
