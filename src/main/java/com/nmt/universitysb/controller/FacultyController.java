package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Faculty;
import com.nmt.universitysb.service.FacultyService;
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
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/faculty")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<Faculty> facultyPage;

        if (keyword != null && !keyword.isEmpty()) {
            facultyPage = this.facultyService.findAllByNameContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            facultyPage = this.facultyService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("facultyPage", facultyPage);
        model.addAttribute("keyword", keyword);

        return "faculty";
    }

    @GetMapping("/add_faculty")
    public String addList(Model model) {
        model.addAttribute("add_faculty", new Faculty());

        return "add_faculty";
    }

    @PostMapping(value = "/add_faculty")
    public String add(@ModelAttribute(value = "add_faculty") @Valid Faculty f,
                      BindingResult rs) {
        if (!rs.hasErrors()) {
            this.facultyService.save(f);
            return "redirect:/faculty";
        }

        return "add_faculty";
    }

    @GetMapping("/update_faculty/{id}")
    public String update(Model model, @PathVariable(value = "id") String id) {
        Optional<Faculty> faculty = this.facultyService.findById(id);
        if (faculty.isPresent()) {
            model.addAttribute("update_faculty", faculty.get());
        } else {
            System.out.println(faculty);
        }

        return "update_faculty";
    }

    @PostMapping("/update_faculty")
    public String update(@ModelAttribute(value = "update_faculty") @Valid Faculty f,
            BindingResult rs) {
        if(!rs.hasErrors()){
            this.facultyService.save(f);
            return "redirect:/faculty";
        }

        return "update_faculty";
    }

}
