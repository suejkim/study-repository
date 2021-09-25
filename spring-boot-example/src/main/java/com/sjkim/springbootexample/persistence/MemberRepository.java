package com.sjkim.springbootexample.persistence;

import com.sjkim.springbootexample.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
