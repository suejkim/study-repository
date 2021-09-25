package com.sjkim.springbootexample.persistence;

import com.sjkim.springbootexample.domain.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {

}
