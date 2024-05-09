package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Score;
import com.nmt.universitysb.service.ScoreService;
import com.nmt.universitysb.service.SemesterService;
import com.nmt.universitysb.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private StudentSubjectService studentSubjectService;

    @GetMapping("/score")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<Score> scorePage;

        if (keyword != null && !keyword.isEmpty()) {
            scorePage = this.scoreService.findAllByIdContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            scorePage = this.scoreService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("scorePage", scorePage);
        model.addAttribute("keyword", keyword);

        return "score";
    }

    @GetMapping("/add_score")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_score", new Score());
        model.addAttribute("semester", this.semesterService.findAll());
        model.addAttribute("student_subject", this.studentSubjectService.findAll());
        return "add_score";
    }

    @GetMapping("/update_score/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_score", this.scoreService.findById(id));
        model.addAttribute("semester", this.semesterService.findAll());
        model.addAttribute("student_subject", this.studentSubjectService.findAll());

        return "update_score";
    }

    @PostMapping("/add_score")
    public String add(@ModelAttribute(value = "add_score") Score u,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            this.scoreService.save(u);
            return "redirect:/score";

        }
        return "add_score";
    }
    @PostMapping("/update_score")
    public String update(@ModelAttribute(value = "update_score") Score u,
                      BindingResult rs) {
        if (!rs.hasErrors()) {
            this.scoreService.save(u);
            return "redirect:/score";

        }
        return "update_score";
    }
}
