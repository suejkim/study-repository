package com.sjkim.springbootexample.persistence;

import com.sjkim.springbootexample.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
