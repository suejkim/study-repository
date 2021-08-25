package com.sjkim.service;

import com.sjkim.BaseTest;
import com.sjkim.model.MemberRole;
import com.sjkim.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class RoleServiceTest extends BaseTest {

    @Autowired
    private RoleService roleService;

    @Test
    void getRoleByMember() {
        String loginId = "admin01";
        List<Role> roles = roleService.getRoleByLoginId(loginId);
        Assertions.assertEquals(MemberRole.ROLE_ADMIN, roles.get(0).getMemberRole());
    }
}