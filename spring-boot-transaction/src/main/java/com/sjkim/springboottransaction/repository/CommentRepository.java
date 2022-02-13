package com.sjkim.springboottransaction.repository;

import com.sjkim.springboottransaction.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
