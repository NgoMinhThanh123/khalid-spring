package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Faculty;
import com.nmt.universitysb.service.FacultyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Faculty Controller")
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiFacultyController {
    @Autowired
    private FacultyService facultyService;

    @DeleteMapping("/update_faculty/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.facultyService.deleteFaculty(id);
    }

    @GetMapping("/faculties/")
    public ResponseEntity<List<Faculty>> list() {
        return new ResponseEntity<>(this.facultyService.findAll(), HttpStatus.OK);
    }

}
