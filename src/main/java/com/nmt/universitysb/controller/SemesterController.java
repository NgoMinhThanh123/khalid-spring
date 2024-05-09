package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Semester;
import com.nmt.universitysb.service.SemesterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/semester")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<Semester> semesterPage;

        if (keyword != null && !keyword.isEmpty()) {
            semesterPage = this.semesterService.findAllBySchoolYearContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            semesterPage = this.semesterService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("semesterPage", semesterPage);
        model.addAttribute("keyword", keyword);

        return "semester";
    }

    @GetMapping("/add_semester")
    public String addList(Model model) {
        model.addAttribute("add_semester", new Semester());

        return "add_semester";
    }

    @GetMapping("/update_semester/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_semester", this.semesterService.findById(id));
        return "update_semester";
    }

    @PostMapping(value = "/add_semester")
    public String add(@ModelAttribute(value = "add_semester") @Valid Semester m,
            BindingResult rs) {
        if(!rs.hasErrors()){
            this.semesterService.save(m);
            return "redirect:/semester";

        }
        return "add_semester";
    }

    @PostMapping("/update_semester")
    public String update(@ModelAttribute(value = "update_semester") @Valid Semester m,
            BindingResult rs) {
        if(!rs.hasErrors()){
            this.semesterService.save(m);
            return "redirect:/semester";

        }
        return "update_semester";
    }
}
