package com.magadhUniversity.controller;

import com.magadhUniversity.model.Student;
import com.magadhUniversity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/create")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    @PostMapping
    public String createStudent(Student student) {
        studentService.createStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/list") // Changed to /list to avoid conflict with /profile
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "list_students";
    }

    @GetMapping("/view/{studentId}")
    public String viewStudent(@PathVariable Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "view_student";
    }

    @GetMapping("/update/{studentId}")
    public String updateStudentForm(@PathVariable Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "update_student";
    }

    @PostMapping("/update/{studentId}")
    public String updateStudent(@PathVariable Long studentId, @RequestParam String name, @RequestParam String department, @RequestParam String year, @RequestParam String email) {
        studentService.updateStudent(studentId, name, department, year, email);
        return "redirect:/students";
    }

    @PostMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    @GetMapping("/profile")
    public String viewProfile(Authentication authentication, Model model) {
        String studentEmail = authentication.getName();
        Student student = studentService.getStudentByEmail(studentEmail);
        model.addAttribute("student", student);
        return "create_student";
    }
}