package com.sjkim.springbootjpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class BookInfoRepositoryTest {
    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Test
    void deleteAllTest() {
        bookInfoRepository.deleteAll();
        var bookInfoOptional = bookInfoRepository.findById(1L);
        var bookInfo = bookInfoOptional.orElse(null);
        assertThat(bookInfo).isNull();
        // 실무에서 사용 X.
        // delete 전에 select query
        // row 수만큼 delete 실행
    }

    @Test
    void deleteAllInBatchTest() {
        bookInfoRepository.deleteAllInBatch();
        var bookInfoOptional = bookInfoRepository.findById(1L);
        var bookInfo = bookInfoOptional.orElse(null);
        assertThat(bookInfo).isNull();
        // 실무에서 사용 X.
        // select 없이 delete 한번만 실행
    }
}
