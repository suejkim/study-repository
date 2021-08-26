package com.sjkim.security;

import com.sjkim.model.Member;
import com.sjkim.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = authenticationHelper.findMember(username);
        List<Role> roles = authenticationHelper.findRole(username);
        List<GrantedAuthority> authorities = authenticationHelper.getGrantedAuthorities(roles);
        return new User(username, member.getPassword(), authorities); // TODO DTO로 변경
    }


}
