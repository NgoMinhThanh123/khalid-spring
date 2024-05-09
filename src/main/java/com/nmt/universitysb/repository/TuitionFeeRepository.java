package com.nmt.universitysb.repository;

import com.nmt.universitysb.dto.TuitionFeeDto;
import com.nmt.universitysb.model.TuitionFee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface TuitionFeeRepository extends JpaRepository<TuitionFee, String> {

    Optional<TuitionFee> findById(int id);
    @Query("select a from TuitionFee a where a.id = :id")
    TuitionFee findByTuitionFeeId(@RequestParam("id") int id);
    Page<TuitionFee> findAllByStudentIdContaining(String keyword, Pageable pageable);
    TuitionFee save(TuitionFee f);
    void deleteById(String id);

    @Query(value ="select new com.nmt.universitysb.dto.TuitionFeeDto(t.id, t.tuitionFee, t.done, t.dateCreated, t.semesterId, t.studentId.id)\n" +
            "FROM TuitionFee t\n" +
            "join Student s on t.studentId.id = s.id\n" +
            "where s.id = :studentId ")
    List<TuitionFeeDto> getTuitionFeeByStudentId(@Param("studentId") String studentId);

    @Query(value ="select new com.nmt.universitysb.dto.TuitionFeeDto(t.id, t.tuitionFee, t.done, t.dateCreated, t.semesterId, t.studentId.id)\n" +
            "FROM TuitionFee t\n" +
            "join Student s on t.studentId.id = s.id\n" +
            "join Semester se on t.semesterId.id = se.id\n" +
            "where s.id = :studentId and se.id = :semesterId")
    List<TuitionFeeDto> getTuitionFeeByStudentIdAndSemesterId(@Param("studentId") String studentId, @Param("semesterId") String semesterId);

}
