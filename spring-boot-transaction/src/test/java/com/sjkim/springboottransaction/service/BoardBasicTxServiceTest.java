package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardBasicTxServiceTest {

    @Autowired
    private BoardBasicTxService boardBasicTxService;
    @Autowired
    private InitData initData;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("@Transactional 미적용")
    void saveBoardAndHistory() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardBasicTxService.saveBoardAndHistory(board, history);
    }

    @Test
    @DisplayName("@Transactional 적용")
    void saveBoardAndHistoryWithTransactional() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardBasicTxService.saveBoardAndHistoryWithTransactional(board, history);
    }

    @Test
    @DisplayName("같은 클래스 내 @Transactional 적용된 메소드 호출. update 실패")
    void beforeSaveAndUpdateInSameClass() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardBasicTxService.beforeSaveAndUpdateInSameClass(board, history);
        board = boardRepository.findById(1L).get();
        assertThat(board.getTitle()).isEqualTo("TITLE");
    }

    @Test
    @DisplayName("같은 클래스 내 @Transactional 적용된 메소드 호출")
    void beforeSaveAndUpdate() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardBasicTxService.beforeSaveAndUpdate(board, history);
        board = boardRepository.findById(1L).get();
        assertThat(board.getTitle()).isEqualTo("TITLE2");
    }
}