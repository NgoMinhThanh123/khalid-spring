package com.nmt.universitysb.controller;

import com.nmt.universitysb.service.ScorePercentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Score Percent Controller")
@RestController
@RequestMapping("/api")
public class ApiScorePercentController {
    @Autowired
    private ScorePercentService scorePercentService;

    @DeleteMapping("/update_score_percent/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.scorePercentService.deleteScorePercent(id);
    }
}
