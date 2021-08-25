package com.sjkim.repository;

import com.sjkim.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByMember_loginId(String loginId);

}
