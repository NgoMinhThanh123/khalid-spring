package com.nmt.universitysb.controller;

import com.nmt.universitysb.service.CreditPriceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Credit Price Controller")
@RestController
@RequestMapping("/api")
public class ApiCreditPriceController {
    @Autowired
    private CreditPriceService creditPriceService;

    @DeleteMapping("/update_credit_price/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.creditPriceService.deleteCreditPrice(id);
    }
}
