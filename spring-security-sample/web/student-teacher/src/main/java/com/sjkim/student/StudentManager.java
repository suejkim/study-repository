package com.sjkim.student;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentManager implements AuthenticationProvider, InitializingBean {  // 통행증 발급

    // 원칙적으로는 DB 핸들링 해야함. 테스트용으로 메모리 객체 사용
    private HashMap<String, Student> studentDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        StudentAuthenticationToken token = (StudentAuthenticationToken) authentication;
        if (this.studentDB.containsKey(token.getCredentials())) {
            Student student = this.studentDB.get(token.getCredentials());
            return StudentAuthenticationToken.builder()
                    .principal(student) // 인증대상 (OUTPUT)
                    .details(student.getUsername()) // 딱히 의미 없음
                    .authenticated(true)
                    .build(); // 인증 토큰 발급
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == StudentAuthenticationToken.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Student("KIM", "김", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")), "CHOI"),
                new Student("LEE", "이", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")), "CHOI"),
                new Student("PARK", "박", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")), "CHOI")
        ).forEach(student -> this.studentDB.put(student.getId(), student));
    }

    public List<Student> myStudentList(String teacherId) {
        return this.studentDB.values().stream()
                .filter(student -> student.getTeacherId().equals(teacherId)).collect(Collectors.toList());
    }
}
