package com.sjkim.springbootexample.persistence.member;

import com.sjkim.springbootexample.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
