package com.nmt.universitysb.controller;

import com.nmt.universitysb.dto.SubjectDto;
import com.nmt.universitysb.dto.TuitionFeeAndSubjectDto;
import com.nmt.universitysb.model.Subject;
import com.nmt.universitysb.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subject Controller")
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiSubjectController {
    @Autowired
    private SubjectService subjectService;

    @DeleteMapping("/update_subject/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.subjectService.deleteSubject(id);
    }

    @GetMapping("/subjects/")
    public ResponseEntity<List<Subject>> list() {
        return new ResponseEntity<>(this.subjectService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/subjects/{lecturerId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDto>> getSubjectByLecturerId(@PathVariable(value = "lecturerId") String lecturerId) {
        List<SubjectDto> list = subjectService.getSubjectByLecturerId(lecturerId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/subjects/studentId/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDto>> getSubjectByStudentId(@RequestParam String studentId) {
        List<SubjectDto> list = subjectService.getSubjectByStudentId(studentId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/subjects/semesterId/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDto>> getSubjectBySemesterId(@RequestParam String semesterId) {
        List<SubjectDto> list = subjectService.getSubjectBySemesterId(semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/subjects/majorId/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDto>> getSubjectByMajorId(@RequestParam String majorId, @RequestParam String semesterId) {
        List<SubjectDto> list = subjectService.getSubjectByMajorId(majorId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/subjects/studentId-semesterId/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDto>> getSubjectByStudentAndSemesterId(
            @RequestParam String studentId,
            @RequestParam String semesterId) {
        List<SubjectDto> list = subjectService.getSubjectByStudentAndSemesterId(studentId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/subjects/temporary-course/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDto>> getSubjectTemporaryCourse(
            @RequestParam String studentId,
            @RequestParam String semesterId) {
        List<SubjectDto> list = subjectService.getSubjectTemporaryCourse(studentId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/subjects/education-program/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDto>> getSubjectByEducationProgram(
            @RequestParam String studentId,
            @RequestParam String semesterId,
            @RequestParam String majorId) {
        List<SubjectDto> list = subjectService.getSubjectByEducationProgram(studentId, semesterId, majorId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/subjects/tuituion-fee/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TuitionFeeAndSubjectDto>> getTuitionFeeOfSemester(
            @RequestParam String studentId,
            @RequestParam String semesterId) {
        List<TuitionFeeAndSubjectDto> list = subjectService.getTuitionFeeOfSemester(studentId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
