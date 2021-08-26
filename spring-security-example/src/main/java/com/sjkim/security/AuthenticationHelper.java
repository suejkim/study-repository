package com.sjkim.security;

import com.sjkim.model.Member;
import com.sjkim.model.Role;
import com.sjkim.repository.MemberRepository;
import com.sjkim.repository.RoleRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationHelper {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    public AuthenticationHelper(MemberRepository memberRepository, RoleRepository roleRepository) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
    }

    public Member findMember(String loginId) {
        Member member = memberRepository.findByLoginId(loginId).orElse(null);
        if (member == null) {
            throw new UsernameNotFoundException(loginId);
        }
        return member;
    }

    public List<Role> findRole(String loginId) {
        return roleRepository.findByMember_loginId(loginId);
    }

    public boolean matchPassword(String password, Object credentials) {
        if(!password.equals(credentials)) {
            throw new BadCredentialsException("not matched");
        }
        return true;
    }

    public List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getMemberRole().name())));
        return authorities;
    }
}
