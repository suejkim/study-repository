package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.repository.BoardRepository;
import com.sjkim.springboottransaction.repository.HistoryRepository;
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
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private HistoryRepository historyRepository;

    @Test
    @DisplayName("@Transactional 미적용")
    void saveBoard() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardBasicTxService.saveBoardAndHistory(board, history);
    }

    @Test
    @DisplayName("@Transactional 적용")
    void saveBoardWithTransactional() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardBasicTxService.saveBoardAndHistoryWithTransactional(board, history);
    }
}