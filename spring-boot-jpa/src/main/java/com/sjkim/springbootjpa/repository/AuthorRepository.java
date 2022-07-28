package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
