package com.nmt.universitysb.controller;

import com.nmt.universitysb.dto.TuitionFeeDto;
import com.nmt.universitysb.service.TuitionFeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tuition Fee Controller")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiTuitionFeeController {
    @Autowired
    private TuitionFeeService tuitionFeeService;

    @DeleteMapping("/update_tuition_fee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.tuitionFeeService.deleteTuitionFee(id);
    }

    @GetMapping("/tuition_fee/student/")
    public ResponseEntity<List<TuitionFeeDto>> getTuitionFeeByStudentId(@RequestParam("studentId") String studentId) {
        List<TuitionFeeDto> list = tuitionFeeService.getTuitionFeeByStudentId(studentId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/tuition_fee/student-semester/")
    public ResponseEntity<List<TuitionFeeDto>> getTuitionFeeByStudentIdAndSemesterId(@RequestParam("studentId") String studentId, @RequestParam("studentId") String semesterId) {
        List<TuitionFeeDto> list = tuitionFeeService.getTuitionFeeByStudentIdAndSemesterId(studentId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
