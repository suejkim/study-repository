package com.sjkim.springboottransaction.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardBasicTxServiceTest {

    @Autowired
    private BoardBasicTxService boardBasicTxService;
    @Autowired
    private InitData initData;

    @Test
    @DisplayName("@Transactional 미적용")
    void saveBoard() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();

        boardBasicTxService.saveBoard(board, history);
    }

    @Test
    @DisplayName("@Transactional 적용")
    void saveBoardWithTransactional() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();

        boardBasicTxService.saveBoardWithTransactional(board, history);
    }
}