package com.sjkim.springboottransaction.repository;

import com.sjkim.springboottransaction.model.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {

}
