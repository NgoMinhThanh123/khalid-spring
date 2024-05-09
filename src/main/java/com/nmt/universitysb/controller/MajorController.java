package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Major;
import com.nmt.universitysb.service.FacultyService;
import com.nmt.universitysb.service.MajorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class MajorController {

    @Autowired
    private MajorService majorService;
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/major")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<Major> majorPage;

        if (keyword != null && !keyword.isEmpty()) {
            majorPage = this.majorService.findAllByNameContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            majorPage = this.majorService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("majorPage", majorPage);
        model.addAttribute("keyword", keyword);

        return "major";
    }

    @GetMapping("/add_major")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_major", new Major());
        model.addAttribute("faculty", this.facultyService.findAll());

        return "add_major";
    }

    @GetMapping("/update_major/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        Optional<Major> major = this.majorService.findById(id);
        if (major.isPresent()) {
            model.addAttribute("update_major", major);
        } else {
            System.out.println(major);
        }
        model.addAttribute("faculty", this.facultyService.findAll());
        return "update_major";
    }

    @PostMapping(value = "/add_major")
    public String add(@ModelAttribute(value = "add_major") @Valid Major m,
            BindingResult rs) {
        if(!rs.hasErrors()){
            this.majorService.save(m);
            return "redirect:/major";

        }

        return "add_major";
    }

    @PostMapping("/update_major")
    public String update(@ModelAttribute(value = "update_major") @Valid Major m, BindingResult rs) {
        if(!rs.hasErrors()){
            this.majorService.save(m);
            return "redirect:/major";

        }
        return "update_major";
    }

}
