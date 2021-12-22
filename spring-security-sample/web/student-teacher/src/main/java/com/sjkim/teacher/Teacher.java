package com.sjkim.teacher;

import com.sjkim.student.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher { // domain principal
    private String id;
    private String username;
    private Set<GrantedAuthority> role;

    private List<Student> studentList;
}
