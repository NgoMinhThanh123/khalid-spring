package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.CreditPrice;
import com.nmt.universitysb.service.CreditPriceService;
import com.nmt.universitysb.service.MajorService;
import com.nmt.universitysb.service.SemesterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CreditPriceController {
    @Autowired
    private CreditPriceService creditPriceService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SemesterService semesterService;

    @GetMapping("/credit_price")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<CreditPrice> creditPricePage;

        if (keyword != null && !keyword.isEmpty()) {
            creditPricePage = this.creditPriceService.findAllByMajorIdContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            creditPricePage = this.creditPriceService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("creditPricePage", creditPricePage);
        model.addAttribute("keyword", keyword);

        return "credit_price";
    }

    @GetMapping("/add_credit_price")
    public String addList(Model model) {
        model.addAttribute("add_credit_price", new CreditPrice());
        model.addAttribute("major", this.majorService.findAll());
        model.addAttribute("semester", this.semesterService.findAll());

        return "add_credit_price";
    }

    @PostMapping(value = "/add_credit_price")
    public String add(@ModelAttribute(value = "add_credit_price") @Valid CreditPrice f,
                      BindingResult rs) {
        if (!rs.hasErrors()) {
            this.creditPriceService.save(f);
            return "redirect:/credit_price";
        }

        return "add_credit_price";
    }

    @GetMapping("/update_credit_price/{id}")
    public String update(Model model, @PathVariable(value = "id") String id) {
        Optional<CreditPrice> creditPrice = this.creditPriceService.findById(id);
        model.addAttribute("major", this.majorService.findAll());
        model.addAttribute("semester", this.semesterService.findAll());
        if (creditPrice.isPresent()) {
            model.addAttribute("update_credit_price", creditPrice.get());
        } else {
            System.out.println(creditPrice);
        }

        return "update_credit_price";
    }

    @PostMapping("/update_credit_price")
    public String update(@ModelAttribute(value = "update_credit_price") @Valid CreditPrice f,
                         BindingResult rs) {
        if(!rs.hasErrors()){
            this.creditPriceService.save(f);
            return "redirect:/credit_price";
        }

        return "update_credit_price";
    }
}
