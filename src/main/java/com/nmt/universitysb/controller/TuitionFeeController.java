package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.TuitionFee;
import com.nmt.universitysb.service.SemesterService;
import com.nmt.universitysb.service.StudentService;
import com.nmt.universitysb.service.TuitionFeeService;
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
public class TuitionFeeController {
    @Autowired
    private TuitionFeeService tuitionFeeService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/tuition_fee")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<TuitionFee> tuitionFeePage;

        if (keyword != null && !keyword.isEmpty()) {
            tuitionFeePage = this.tuitionFeeService.findAllByStudentIdContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            tuitionFeePage = this.tuitionFeeService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("tuitionFeePage", tuitionFeePage);
        model.addAttribute("keyword", keyword);

        return "tuition_fee";
    }

    @GetMapping("/add_tuition_fee")
    public String addList(Model model) {
        model.addAttribute("add_tuition_fee", new TuitionFee());
        model.addAttribute("semester", this.semesterService.findAll());
        model.addAttribute("student", this.studentService.findAll());

        return "add_tuition_fee";
    }

    @PostMapping(value = "/add_tuition_fee")
    public String add(@ModelAttribute(value = "add_tuition_fee") @Valid TuitionFee f,
                      BindingResult rs) {
        if (!rs.hasErrors()) {
            this.tuitionFeeService.save(f);
            return "redirect:/tuition_fee";
        }

        return "add_tuition_fee";
    }

    @GetMapping("/update_tuition_fee/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        Optional<TuitionFee> tuitionFee = this.tuitionFeeService.findById(id);
        model.addAttribute("semester", this.semesterService.findAll());
        model.addAttribute("student", this.studentService.findAll());
        if (tuitionFee.isPresent()) {
            model.addAttribute("update_tuition_fee", tuitionFee.get());
        } else {
            System.out.println(tuitionFee);
        }

        return "update_tuition_fee";
    }

    @PostMapping("/update_tuition_fee")
    public String update(@ModelAttribute(value = "update_tuition_fee") @Valid TuitionFee f,
                         BindingResult rs) {
        if(!rs.hasErrors()){
            this.tuitionFeeService.save(f);
            return "redirect:/tuition_fee";
        }

        return "update_tuition_fee";
    }
}
