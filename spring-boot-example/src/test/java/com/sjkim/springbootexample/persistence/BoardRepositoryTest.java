package com.sjkim.springbootexample.persistence;

import com.sjkim.springbootexample.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void findAll() {
        var boards = boardRepository.findAll();
        var count = boardRepository.count();
        assertThat(boards.size()).isEqualTo(count);
    }

    private Board buildBoard() {
        return Board.builder().content("CONTENT").title("TITLE").writer("WRITER").build();
    }

    @Test
    @Rollback(value = false)
    void add() {
        var board = buildBoard();
        boardRepository.save(board);
        assertThat(board).isNotNull();
    }

    @Test
    @Rollback(value = false)
    void update() {
        var boardOptional = boardRepository.findById(1L); // 특정 id 지정 금지
        if (boardOptional.isPresent()) {
            var board = boardOptional.get();
            board.changeTitle("스프링");
            boardRepository.save(board);
            assertThat(board.getId()).isEqualTo(1L);
        }
    }

    @Test
    @Rollback(value = false)
    void delete() {
        var boardOptional = boardRepository.findById(1L);
        var boardList = boardRepository.findAll();
        if (boardOptional.isPresent()) {
            var board = boardOptional.get();
            boardRepository.delete(board);
            var afterBoardList = boardRepository.findAll();
            assertThat(boardList).isNotEqualTo(afterBoardList);
        }
    }
}