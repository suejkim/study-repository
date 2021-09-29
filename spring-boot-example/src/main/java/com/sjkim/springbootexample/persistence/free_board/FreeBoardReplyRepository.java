package com.sjkim.springbootexample.persistence.free_board;

import com.sjkim.springbootexample.domain.FreeBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardReplyRepository extends JpaRepository<FreeBoardReply, Long> {

}
