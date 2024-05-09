package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Classes;
import com.nmt.universitysb.service.ClassesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Class Controller")
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiClassController {
    @Autowired
    private ClassesService classesService;

    @DeleteMapping("/update_class/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.classesService.deleteClasse(id);
    }

    @GetMapping("/classes/")
    public ResponseEntity<List<Classes>> list() {
        return new ResponseEntity<>(this.classesService.findAll(), HttpStatus.OK);
    }

}
