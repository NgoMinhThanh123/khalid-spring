package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.Student;
import com.nmt.universitysb.service.*;
import com.nmt.universitysb.utils.ExcelStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private ExcelStudentService excelService;

    @GetMapping("/student")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<Student> studentPage;

        if (keyword != null && !keyword.isEmpty()) {
            studentPage = this.studentService.findAllByNameContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            studentPage = this.studentService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("studentPage", studentPage);
        model.addAttribute("keyword", keyword);

        return "student";
    }

    @GetMapping("/add_student")
    public String addList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("add_student", new Student());
        model.addAttribute("user", this.userService.findAll());
        model.addAttribute("classes", this.classesService.findAll());
        model.addAttribute("faculty", this.facultyService.findAll());
        model.addAttribute("major", this.majorService.findAll());

        return "add_student";
    }

    @GetMapping("/update_student/{id}")
    public String update(Model model, @PathVariable(value = "id") String id, @RequestParam Map<String, String> params) {
        model.addAttribute("update_student", this.studentService.findById(id));
        model.addAttribute("user", this.userService.findAll());
        model.addAttribute("classes", this.classesService.findAll());
        model.addAttribute("faculty", this.facultyService.findAll());
        model.addAttribute("major", this.majorService.findAll());
        return "update_student";
    }

    @PostMapping(value = "/add_student")
    public String add(@ModelAttribute(value = "add_student") @Valid Student s,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            this.studentService.save(s);
            return "redirect:/student";

        }
        return "add_student";
    }

    @PostMapping("/update_student")
    public String update(@ModelAttribute(value = "update_student") @Valid Student s,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            this.studentService.save(s);
            return "redirect:/student";

        }

        return "update_student";
    }

    @PostMapping("/students/upload/")
    @CrossOrigin
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty.");
            }
            if (!file.getOriginalFilename().endsWith(".xlsx")) {
                throw new IllegalArgumentException("File format is not supported. Please upload an Excel file.");
            }

            Path tempDir = Files.createTempDirectory("upload-dir");

            Path tempFile = tempDir.resolve(file.getOriginalFilename());

            Files.write(tempFile, file.getBytes());

            List<Student> students = excelService.readStudentsFromExcelFile(tempFile.toFile());

            // Xóa thư mục tạm thời và tất cả các tệp tin bên trong sau khi đã xử lý xong
            Files.walk(tempDir)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            this.studentService.save(students);
            return ResponseEntity.ok("File uploaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
        }
    }
}
