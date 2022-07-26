package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.Author;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Slf4j
@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void authorEntityListenersTest() {
        var books = bookRepository.findAll();
        var author = Author.builder().books(books).comment(UUID.randomUUID().toString()).name("TEST").build();
        authorRepository.save(author);
    }

}
