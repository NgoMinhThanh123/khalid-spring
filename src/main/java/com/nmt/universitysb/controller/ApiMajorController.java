package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Major;
import com.nmt.universitysb.service.MajorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Major Controller")
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiMajorController {
    @Autowired
    private MajorService majorService;

    @DeleteMapping("/update_major/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.majorService.deleteMajor(id);
    }

    @GetMapping("/majors/")
    public ResponseEntity<List<Major>> list() {
        return new ResponseEntity<>(this.majorService.findAll(), HttpStatus.OK);
    }
}
