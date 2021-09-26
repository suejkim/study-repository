package com.sjkim.springbootexample.persistence.member;

import com.sjkim.springbootexample.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
