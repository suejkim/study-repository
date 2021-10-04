package com.sjkim.springbootexample.service;

import com.sjkim.springbootexample.dto.BoardSaveDto;
import com.sjkim.springbootexample.persistence.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    void deleteBoard() {
        var boards = boardRepository.findAll();
        var boardOptional = boards.stream().findAny();
        if (boardOptional.isPresent()) {
            var board = boardOptional.get();
            boolean result = boardService.deleteBoard(board.getId());
            assertThat(result).isTrue();
        }
    }

    @Test
    void updateBoard() {
        var boards = boardRepository.findAll();
        var boardOptional = boards.stream().findAny();
        if (boardOptional.isPresent()) {
            var board = boardOptional.get();
            var result = boardService.updateBoard(board.getId(), "UPDATE_CONTENT_NEW");
            assertThat(result).isTrue();
        }
    }

    @Test
    void addBoard() {
        var boardSaveDto = BoardSaveDto.builder().title(UUID.randomUUID().toString())
                .content(UUID.randomUUID().toString()).writer("TEST").build();
        var result = boardService.addBoard(boardSaveDto);
        assertThat(result).isTrue();
    }

    @Test
    void getBoardList() {
        String title = UUID.randomUUID().toString();
        var boardSaveDto = BoardSaveDto.builder().title(title)
                .content(UUID.randomUUID().toString()).writer("TEST").build();
        boardService.addBoard(boardSaveDto);

        var boards = boardService.getBoardList(title, null, null);
        assertThat(boards.size()).isEqualTo(1);
    }
}