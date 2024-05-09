package com.nmt.universitysb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.nmt.universitysb.dto.StuScoreDto;
import com.nmt.universitysb.dto.StudentDto;
import com.nmt.universitysb.model.Student;
import com.nmt.universitysb.model.Subject;
import com.nmt.universitysb.requests.MailRequest;
import com.nmt.universitysb.service.MailService;
import com.nmt.universitysb.service.StudentService;
import com.nmt.universitysb.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Tag(name = "Student Controller")
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiStudentController {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private MailService mailService;

    @DeleteMapping("/update_student/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        this.studentService.deleteStudent(id);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> list() {
        return new ResponseEntity<>(this.studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}/")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") String id) {
        Optional<Student> studentOptional = studentService.findById(id);

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Student student = studentOptional.get();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/students-un/{username}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> getStudentByUsename(@PathVariable(value = "username") String username) {
        StudentDto studentDto = studentService.getStudentByUsername(username);
        if (studentDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }


    @GetMapping(path = "/students-lecturer/{lecturerId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDto>> getStudentByHomeroomTeacher(@PathVariable(value = "lecturerId") String lecturerId) {
        List<StudentDto> students = studentService.getStudentByHomeroomTeacher(lecturerId);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(path = "/get-list-student/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StuScoreDto>> getListStudent(
            @RequestParam String lecturerId,
            @RequestParam String classId,
            @RequestParam String subjectId,
            @RequestParam String semesterId) {
        List<StuScoreDto> list = studentService.getListStudent(lecturerId, classId, subjectId, semesterId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/get-student-parents/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> getListStudentForParents(
            @RequestParam String studentId,
            @RequestParam String studentName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date studentBirthday,
            @RequestParam String classId,
            @RequestParam String studentIdentification) {
        StudentDto s = studentService.getListStudentForParents(studentId, studentName, studentBirthday, classId, studentIdentification);
        if (s == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping("/students/mails/")
    public ResponseEntity<?> sendMailToStudent(
            @RequestParam String lecturerId,
            @RequestParam String subjectId,
            @RequestParam String semesterId) {
        Optional<Subject> s = this.subjectService.findById(subjectId);
        String subject = "Đã có điểm môn học";
        String body = "Đã có điểm môn học " + s.get().getName() + ", các em vào xem nhé!!!";
        String from = "2051052127thanh@ou.edu.vn";
        List<String> listMail = studentService.getAllMailOfStudent(lecturerId, subjectId, semesterId);
        listMail.stream().forEach(m -> {
            MailRequest mailRequest = MailRequest.builder()
                    .date(LocalDate.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD)))
                    .body(body)
                    .subject(subject)
                    .from(from)
                    .recipients(m)
                    .build();
            mailService.sendMailToStudent(mailRequest);
        });
        return ResponseEntity.ok("Send mail to group successfully!");
    }

}
