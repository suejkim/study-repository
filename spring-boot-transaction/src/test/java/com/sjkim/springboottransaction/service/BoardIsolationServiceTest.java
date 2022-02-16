package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardIsolationServiceTest {

    @Autowired
    private BoardIsolationService boardIsolationService;
    @Autowired
    private InitData initData;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void saveBoard() {
        var board = initData.buildBoard();
        boardIsolationService.saveBoard(board);

        boardIsolationService.getBoard(1L);

        board = boardRepository.findById(1L).get(); // 별도의 트랜잭션
    }
}