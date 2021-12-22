package com.sjkim.controller;

import com.sjkim.student.Student;
import com.sjkim.student.StudentManager;
import com.sjkim.teacher.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teacher")
public class ApiTeacherController {

    private final StudentManager studentManager;

    @PreAuthorize("hasAnyAuthority('ROLE_TEACHER')")
    @GetMapping(value =  "/students")
    public List<Student> getStudents(@AuthenticationPrincipal Teacher teacher) {
        return studentManager.myStudentList(teacher.getId());
    }

}
