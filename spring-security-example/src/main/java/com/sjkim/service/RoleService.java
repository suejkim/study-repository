package com.sjkim.service;

import com.sjkim.model.Role;
import com.sjkim.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getRoleByLoginId(String loginId) {
        return roleRepository.findByMember_loginId(loginId);
    }
}
