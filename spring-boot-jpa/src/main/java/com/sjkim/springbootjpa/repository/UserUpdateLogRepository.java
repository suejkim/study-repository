package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.UserUpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserUpdateLogRepository extends JpaRepository<UserUpdateLog, Long> {
    List<UserUpdateLog> findByUserIdOrderByIdDesc(Long userId);
}
