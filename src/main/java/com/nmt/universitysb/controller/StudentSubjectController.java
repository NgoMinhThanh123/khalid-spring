package com.nmt.universitysb.controller;
import com.nmt.universitysb.model.StudentSubject;
import com.nmt.universitysb.service.StudentService;
import com.nmt.universitysb.service.StudentSubjectService;
import com.nmt.universitysb.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class StudentSubjectController {
    @Autowired
    private StudentSubjectService studentSubjectService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/student_subject")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<StudentSubject> studentSubjectPage;

        if (keyword != null && !keyword.isEmpty()) {
            studentSubjectPage = this.studentSubjectService.findAllByStudentIdContaining(String.valueOf(keyword), PageRequest.of(page, pageSize));
        } else {
            studentSubjectPage = this.studentSubjectService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("studentSubjectPage", studentSubjectPage);
        model.addAttribute("keyword", keyword);

        return "student_subject";
    }

    @GetMapping("/add_student_subject")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_student_subject", new StudentSubject());
        model.addAttribute("student", this.studentService.findAll());
        model.addAttribute("subject", this.subjectService.findAll());

        return "add_student_subject";
    }

    @GetMapping("/update_student_subject/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_student_subject", this.studentSubjectService.findById(id));
        model.addAttribute("student", this.studentService.findAll());
        model.addAttribute("subject", this.subjectService.findAll());

        return "update_student_subject";
    }

    @PostMapping("/add_student_subject")
    public String add(@ModelAttribute(value = "add_student_subject") StudentSubject u,
                      BindingResult rs) {
        if (!rs.hasErrors()) {
            this.studentSubjectService.save(u);
            return "redirect:/student_subject";

        }
        return "add_student_subject";
    }

    @PostMapping("/update_student_subject")
    public String update(@ModelAttribute(value = "update_student_subject") StudentSubject u,
                      BindingResult rs) {
        if (!rs.hasErrors()) {
            this.studentSubjectService.save(u);
            return "redirect:/student_subject";

        }
        return "update_student_subject";
    }
}
