package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Classes;
import com.nmt.universitysb.service.ClassesService;
import com.nmt.universitysb.service.FacultyService;
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
public class ClassController {

    @Autowired
    private ClassesService classesService;
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/classes")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<Classes> classesPage;

        if (keyword != null && !keyword.isEmpty()) {
            classesPage = this.classesService.findAllByIdContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            classesPage = this.classesService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("classesPage", classesPage);
        model.addAttribute("keyword", keyword);

        return "classes";
    }

    @GetMapping("/add_class")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_class", new Classes());
        model.addAttribute("faculty", this.facultyService.findAll());

        return "add_class";
    }

    @GetMapping("/update_class/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_class", this.classesService.findById(id));
        model.addAttribute("faculty", this.facultyService.findAll());
        return "update_class";
    }

    @PostMapping(value = "/add_class")
    public String add(@ModelAttribute(value = "add_class") @Valid Classes c,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            this.classesService.save(c);
            return "redirect:/classes";

        }

        return "add_class";
    }

    @PostMapping("/update_class")
    public String update(@ModelAttribute(value = "update_class") @Valid Classes c,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            this.classesService.save(c);
            return "redirect:/classes";
        }

        return "update_class";
    }
}
