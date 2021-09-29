package com.sjkim.springbootexample.persistence.free_board;

import com.sjkim.springbootexample.domain.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
}
