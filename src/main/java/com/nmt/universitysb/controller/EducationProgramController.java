package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.EducationProgram;
import com.nmt.universitysb.service.EducationProgramService;
import com.nmt.universitysb.service.MajorService;
import com.nmt.universitysb.service.SemesterService;
import com.nmt.universitysb.service.SubjectService;
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
public class EducationProgramController {

    @Autowired
    private EducationProgramService educationProgramService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private MajorService majorService;

    @GetMapping("/education_program")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<EducationProgram> educationProgramPage;

        if (keyword != null && !keyword.isEmpty()) {
            educationProgramPage = this.educationProgramService.findAllByMajorIdContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            educationProgramPage = this.educationProgramService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("educationProgramPage", educationProgramPage);
        model.addAttribute("keyword", keyword);

        return "education_program";
    }

    @GetMapping("/add_education_program")
    public String addList(Model model) {
        model.addAttribute("add_education_program", new EducationProgram());
        model.addAttribute("subject", this.subjectService.findAll());
        model.addAttribute("semester", this.semesterService.findAll());
        model.addAttribute("major", this.majorService.findAll());



        return "add_education_program";
    }

    @GetMapping("/update_education_program/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_education_program", this.educationProgramService.findById(id));
        model.addAttribute("subject", this.subjectService.findAll());
        model.addAttribute("semester", this.semesterService.findAll());
        model.addAttribute("major", this.majorService.findAll());

        return "update_education_program";
    }

    @PostMapping(value = "/add_education_program")
    public String add(@ModelAttribute(value = "add_education_program") @Valid EducationProgram m,
                      BindingResult rs) {
        if(!rs.hasErrors()){
            this.educationProgramService.save(m);
            return "redirect:/education_program";

        }
        return "add_education_program";
    }

    @PostMapping("/update_education_program")
    public String update(@ModelAttribute(value = "update_education_program") @Valid EducationProgram m,
                         BindingResult rs) {
        if(!rs.hasErrors()){
            this.educationProgramService.save(m);
            return "redirect:/education_program";

        }
        return "update_education_program";
    }
}
