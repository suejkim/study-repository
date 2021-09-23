package com.sjkim.springbootexample.persistence;

import com.sjkim.springbootexample.domain.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.util.AssertionErrors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@ExtendWith(MockitoExtension.class)
class BoardRepositoryTest {

    //    @Autowired
    @Mock
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

    private Board buildBoardForFindId() {
        return Board.builder().id(1L).content("CONTENT").title("TITLE").writer("WRITER").build();
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

    @Test
    void addBoardMockitoTest() {
        when(this.boardRepository.save(any(Board.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        Board expected = buildBoard();
        Board actual = this.boardRepository.save(expected);
        assertThat(expected.getTitle()).isEqualTo(actual.getTitle());
        assertThat(expected.getContent()).isEqualTo(actual.getContent());
    }

    @Test
    void findBoardMockitoTest() {
        var board = buildBoardForFindId();
        Optional<Board> optionalBoard = Optional.of(board);
        given(this.boardRepository.findById(anyLong()))
                .willReturn(optionalBoard);

        var actual = this.boardRepository.findById(board.getId()).get();
        var expected = optionalBoard.get();

        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    void findBySearchTitleTest() {
        var predicate = boardRepository.findBySearchTitle("TITLE");
        var iterable = boardRepository.findAll(predicate);
        List<Board> boards = new ArrayList<>();
        iterable.forEach(boards::add);
        if (!boards.isEmpty()) {
            Assertions.assertEquals("TITLE", boards.get(0).getTitle());
        }
    }
}