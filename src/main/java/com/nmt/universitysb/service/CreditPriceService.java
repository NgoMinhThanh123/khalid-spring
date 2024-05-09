package com.nmt.universitysb.service;

import com.nmt.universitysb.model.CreditPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CreditPriceService {
    List<CreditPrice> findAll();
    Page<CreditPrice> findAll(Pageable pageable);
    Optional<CreditPrice> findById(String id);
    Page<CreditPrice> findAllByMajorIdContaining(String keyword, Pageable pageable);
    CreditPrice save(CreditPrice f);
    long count();
    boolean deleteCreditPrice(String id);
}
