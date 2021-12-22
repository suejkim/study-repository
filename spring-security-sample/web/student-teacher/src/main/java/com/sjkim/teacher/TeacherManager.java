package com.sjkim.teacher;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class TeacherManager implements AuthenticationProvider, InitializingBean {  // 통행증 발급

    // 원칙적으로는 DB 핸들링 해야함. 테스트용으로 메모리 객체 사용
    private HashMap<String, Teacher> teacherDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TeacherAuthenticationToken token = (TeacherAuthenticationToken) authentication;
        if (this.teacherDB.containsKey(token.getCredentials())) {
            Teacher teacher = this.teacherDB.get(token.getCredentials());
            return TeacherAuthenticationToken.builder()
                    .principal(teacher) // 인증대상 (OUTPUT)
                    .details(teacher.getUsername()) // 딱히 의미 없음
                    .authenticated(true)
                    .build(); // 인증 토큰 발급
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == TeacherAuthenticationToken.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Teacher("CHOI", "최", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER")), null)
        ).forEach(student -> this.teacherDB.put(student.getId(), student));
    }
}
