package com.sjkim.springbootexample.persistence;

import com.sjkim.springbootexample.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardRepositoryPagingTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void addTestData() {
        for (var i = 0; i < 10; i++) {
            var board = Board.builder()
                    .title("TITLE_" + i)
                    .content("CONTENT_" + i)
                    .writer("WRITER_" + i)
                    .build();
            boardRepository.save(board);
        }
    }

    @Test
    void findByWriterOrderByTitleDesc() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "title");
//        var page = boardRepository.findByIdGreaterThan(0L, pageable);
//        var list = page.getContent();
        var list = boardRepository.findByIdGreaterThan(0L, pageable);
        assertThat(list.get(0).getTitle()).isEqualTo("TITLE_9");
    }

    @Test
    void findByTitleTest() {
        var list = boardRepository.findByTitle("TI");
        assertThat(list.size()).isEqualTo(boardRepository.count());
    }

    @Test
    void findByContentTest() {
        var list = boardRepository.findByContent("CONT");
        assertThat(list.size()).isEqualTo(boardRepository.count());
    }

    @Test
    void findByWriterTest() {
        var list = boardRepository.findByWriter("WRITER");
        assertThat(list.size()).isEqualTo(1);
    }
}
