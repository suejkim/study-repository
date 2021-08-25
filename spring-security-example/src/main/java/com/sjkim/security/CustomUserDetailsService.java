package com.sjkim.security;

import com.sjkim.model.Member;
import com.sjkim.model.Role;
import com.sjkim.service.MemberService;
import com.sjkim.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = findMember(username);
        List<Role> roles = findRole(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getMemberRole().name())));
        return new User(username, member.getPassword(), authorities);
    }

    private Member findMember(String loginId) {
        Member member = memberService.getMemberByLoginId(loginId);
        if (member == null) {
            throw new UsernameNotFoundException(loginId);
        }
        return member;
    }

    private List<Role> findRole(String loginId) {
        return roleService.getRoleByLoginId(loginId);
    }
}
