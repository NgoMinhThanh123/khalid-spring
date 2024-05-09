package com.nmt.universitysb.service.impl;

import com.nmt.universitysb.model.CreditPrice;
import com.nmt.universitysb.repository.CreditPriceRepository;
import com.nmt.universitysb.service.CreditPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditPriceServiceImpl implements CreditPriceService {
    @Autowired
    private CreditPriceRepository creditPriceRepository;

    @Override
    public List<CreditPrice> findAll() {
        return this.creditPriceRepository.findAll();
    }

    @Override
    public Page<CreditPrice> findAll(Pageable pageable) {
        return this.creditPriceRepository.findAll(pageable);
    }

    @Override
    public Optional<CreditPrice> findById(String id) {
        return this.creditPriceRepository.findById(id);
    }

    @Override
    public Page<CreditPrice> findAllByMajorIdContaining(String keyword, Pageable pageable) {
        return this.creditPriceRepository.findAllByMajorIdContaining(keyword, pageable);
    }

    @Override
    public CreditPrice save(CreditPrice f) {
        return this.creditPriceRepository.save(f);
    }

    @Override
    public long count() {
        return this.creditPriceRepository.count();
    }

    @Override
    public boolean deleteCreditPrice(String id) {
        this.creditPriceRepository.deleteById(id);
        return true;
    }
}
