package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.ScorePercent;
import com.nmt.universitysb.service.ScorePercentService;
import com.nmt.universitysb.service.SubjectService;
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
public class ScorePercentController {
    @Autowired
    private ScorePercentService scorePercentService;
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/score_percent")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<ScorePercent> scorePercentPage;

        if (keyword != null && !keyword.isEmpty()) {
            scorePercentPage = this.scorePercentService.findAllBySubjectIdContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            scorePercentPage = this.scorePercentService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("scorePercentPage", scorePercentPage);
        model.addAttribute("keyword", keyword);

        return "score_percent";
    }

    @GetMapping("/add_score_percent")
    public String addList(Model model) {
        model.addAttribute("add_score_percent", new ScorePercent());
        model.addAttribute("subject", this.subjectService.findAll());

        return "add_score_percent";
    }

    @PostMapping(value = "/add_score_percent")
    public String add(@ModelAttribute(value = "add_score_percent") @Valid ScorePercent f,
                      BindingResult rs) {
        if (!rs.hasErrors()) {
            this.scorePercentService.save(f);
            return "redirect:/score_percent";
        }

        return "add_score_percent";
    }

    @GetMapping("/update_score_percent/{id}")
    public String update(Model model, @PathVariable(value = "id") String id) {
        Optional<ScorePercent> scorePercent = this.scorePercentService.findById(id);
        model.addAttribute("subject", this.subjectService.findAll());
        if (scorePercent.isPresent()) {
            model.addAttribute("update_score_percent", scorePercent.get());
        } else {
            System.out.println(scorePercent);
        }

        return "update_score_percent";
    }

    @PostMapping("/update_score_percent")
    public String update(@ModelAttribute(value = "update_score_percent") @Valid ScorePercent f,
                         BindingResult rs) {
        if(!rs.hasErrors()){
            this.scorePercentService.save(f);
            return "redirect:/score_percent";
        }

        return "update_score_percent";
    }
}
