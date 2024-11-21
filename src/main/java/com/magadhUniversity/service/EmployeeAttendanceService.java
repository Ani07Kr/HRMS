package com.magadhUniversity.service;

import com.magadhUniversity.model.EmployeeAttendance;
import java.util.List;

public interface EmployeeAttendanceService {
    EmployeeAttendance markAttendance(EmployeeAttendance attendance);
    List<EmployeeAttendance> getAttendanceByEmployeeId(Long employeeId);
    List<EmployeeAttendance> getAllAttendanceRecords();
    List<EmployeeAttendance> getAllEmployeeAttendanceRecords();
    void markAttendance(Long employeeId, String date, boolean present);
    // Add this method
    void deleteAttendance(Long attendanceId);
}
