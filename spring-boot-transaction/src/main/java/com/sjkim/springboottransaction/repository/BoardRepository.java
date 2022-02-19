package com.sjkim.springboottransaction.repository;

import com.sjkim.springboottransaction.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
