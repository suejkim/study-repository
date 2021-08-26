package com.sjkim.security;

import com.sjkim.model.Member;
import com.sjkim.model.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private AuthenticationHelper authenticationHelper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        Member member = authenticationHelper.findMember(authenticationToken.getName());
        authenticationHelper.matchPassword(member.getPassword(), authenticationToken.getCredentials());
        List<Role> roles = authenticationHelper.findRole(authenticationToken.getName());
        List<GrantedAuthority> authorities = authenticationHelper.getGrantedAuthorities(roles);
        UserInfo userInfo = UserInfo.builder().loginId(member.getLoginId()).build();
        return new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
