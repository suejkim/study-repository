package com.sjkim.springbootjpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @DisplayName("findById()와 비교")
    @Transactional
        // LazyInitializationException: could not initialize proxy - no Session error
    void getByIdTest() {
        log.info("getById() 호출");
        var bookInfo = bookInfoRepository.getById(1L);
        log.info("getter Book");
        bookInfo.getBook();
        // getter Book 조회시 select query 실행
        // getReference() 통해 프록시객체만 리턴되고 실제 값을 구하는 시점(getter book)에 Entity 조회
        // fetchType은 LAZY
    }

    @Test
    void findByIdTest() {
        log.info("findById() 호출");
        var bookInfo = bookInfoRepository.findById(1L);
        // findById() 호출하면서 select query 실행되어 직접 Entity 조회. fetchType은 EAGER
        assertThat(bookInfo).isNotNull();
    }
}
