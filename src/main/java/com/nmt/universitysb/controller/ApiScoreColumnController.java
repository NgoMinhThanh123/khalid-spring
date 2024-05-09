package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.ScoreColumn;
import com.nmt.universitysb.service.ScoreColumnSevice;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ScoreColumn Controller")
@RestController
@RequestMapping("/api")
public class ApiScoreColumnController {
    @Autowired
    private ScoreColumnSevice scoreColumnSevice;

    @DeleteMapping("/delete_score_column/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.scoreColumnSevice.deleteScoreColumn(id);
    }

    @GetMapping(path = "/score_column/name/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreColumn> getScoreColumnByName(
            @RequestParam String name) {
        ScoreColumn scoreColumn = scoreColumnSevice.getScoreColumnByName(name);
        if (scoreColumn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(scoreColumn, HttpStatus.OK);
    }
}
