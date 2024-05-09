package com.nmt.universitysb.controller;

import com.nmt.universitysb.service.FacultyService;
import com.nmt.universitysb.service.LecturerService;
import com.nmt.universitysb.service.MajorService;
import com.nmt.universitysb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/")
    public String index(Model model) {
        long countFaculty = this.facultyService.count();
        long countMajor = this.majorService.count();
        long countLecturer = this.lecturerService.count();
        long countStudent = this.studentService.count();

        model.addAttribute("countFaculty", countFaculty);
        model.addAttribute("countMajor", countMajor);
        model.addAttribute("countLecturer", countLecturer);
        model.addAttribute("countStudent", countStudent);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/success")
    public String success() {
        return "success";
    }
    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "cancel";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "testdocker";
    }

}
