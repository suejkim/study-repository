package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
