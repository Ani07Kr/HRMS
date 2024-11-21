package com.magadhUniversity.controller;

import com.magadhUniversity.model.Announcement;
import com.magadhUniversity.model.Attendance;
import com.magadhUniversity.model.Employee;
import com.magadhUniversity.model.EmployeeAttendance;
import com.magadhUniversity.model.Student;
import com.magadhUniversity.model.StudentMarks;
import com.magadhUniversity.model.Subject;
import com.magadhUniversity.service.AnnouncementService;
import com.magadhUniversity.service.AttendanceService;
import com.magadhUniversity.service.EmployeeAttendanceService;
import com.magadhUniversity.service.EmployeeService;
import com.magadhUniversity.service.StudentMarksService;
import com.magadhUniversity.service.StudentService;
import com.magadhUniversity.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentMarksService studentMarksService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private EmployeeAttendanceService employeeAttendanceService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<StudentMarks> studentMarks = studentMarksService.getAllStudentMarks();
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        List<Attendance> studentAttendance = attendanceService.getAllAttendanceRecords();
        List<EmployeeAttendance> employeeAttendance = employeeAttendanceService.getAllEmployeeAttendanceRecords();

        model.addAttribute("employees", employees);
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        model.addAttribute("studentMarks", studentMarks);
        model.addAttribute("announcements", announcements);
        model.addAttribute("studentAttendance", studentAttendance);
        model.addAttribute("employeeAttendance", employeeAttendance);

        return "admin-dashboard";
    }

    @PostMapping("/announcements/delete/{announcementId}")
    public String deleteAnnouncement(@PathVariable Long announcementId) {
        announcementService.deleteAnnouncement(announcementId);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/attendance/mark-employee-attendance")
    public String markEmployeeAttendance(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "mark-employee-attendance";
    }

    @PostMapping("/attendance/mark-employee-attendance")
    public String saveEmployeeAttendance(
            @RequestParam Long employeeId,
            @RequestParam String date,
            @RequestParam boolean present) {
        employeeAttendanceService.markAttendance(employeeId, date, present);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/attendance/view_student_attendance")
    public String viewStudentAttendance(Model model) {
        model.addAttribute("studentAttendance", attendanceService.getAllAttendanceRecords());
        return "view-student-attendance";
    }

    @GetMapping("/employees/attendance/view")
    public String viewEmployeeAttendance(Model model) {
        model.addAttribute("employeeAttendance", employeeAttendanceService.getAllEmployeeAttendanceRecords());
        return "view_attendance";
    }

    // ... other methods for managing employees, students, subjects, etc. ...
}