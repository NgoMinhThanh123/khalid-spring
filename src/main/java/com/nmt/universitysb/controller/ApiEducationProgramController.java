package com.nmt.universitysb.controller;

import com.nmt.universitysb.service.EducationProgramService;
import com.nmt.universitysb.service.FacultyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Education Program Controller")
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiEducationProgramController {
    @Autowired
    private EducationProgramService educationProgramService;

    @DeleteMapping("/update_education_program/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.educationProgramService.deleteEducationProgram(id);
    }
}
