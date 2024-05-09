package com.nmt.universitysb.repository;

import com.nmt.universitysb.dto.creditPriceDto;
import com.nmt.universitysb.model.CreditPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditPriceRepository extends JpaRepository<CreditPrice, String> {
    Optional<CreditPrice> findById(String id);

    Page<CreditPrice> findAllByMajorIdContaining(String keyword, Pageable pageable);
    CreditPrice save(CreditPrice f);
    void deleteById(String id);
    @Query("select new com.nmt.universitysb.dto.creditPriceDto(c.price, c.majorId, c.semesterId) " +
            "from CreditPrice c " +
            "join Major m on m.id = c.majorId.id " +
            "join Semester s on s.id = c.semesterId.id " +
            "where m.id = :majorId and s.schoolYear = :schoolYear")
    creditPriceDto getcreditPriceByMajorIdAndSchoolYear(@Param("majorId") String majorId, @Param("schoolYear") int schoolYear);
}
