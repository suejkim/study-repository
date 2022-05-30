package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {
}
