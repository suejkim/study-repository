package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.repository.BoardRepository;
import com.sjkim.springboottransaction.repository.HistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BoardErrorTxServiceTest {

    @Autowired
    private BoardErrorTxService boardErrorTxService;
    @Autowired
    private InitData initData;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private HistoryRepository historyRepository;

    @Test
    @DisplayName("RuntimeException발생. @Transactional 미적용")
    void saveBoardAndHistory() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();

        assertThrows(RuntimeException.class, () -> {
            boardErrorTxService.save(board, history);
        });

        assertThat(boardRepository.count()).isEqualTo(1);
        assertThat(historyRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("RuntimeException발생. @Transacional 적용")
    void saveBoardAndHistoryWithTransactional() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(RuntimeException.class, () -> {
            boardErrorTxService.saveWithTransactional(board, history);
        });
        assertThat(boardRepository.count()).isZero();
        assertThat(historyRepository.count()).isZero();
    }

    @Test
    @DisplayName("Exception발생. @Transacional 적용")
    void occurException() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(Exception.class, () -> {
            boardErrorTxService.occurException(board, history);
        });
        assertThat(boardRepository.count()).isEqualTo(1);
        assertThat(historyRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("Exception발생. @Transacional RollbackFor 적용")
    void occurExceptionApplyRollbackFor() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(Exception.class, () -> {
            boardErrorTxService.occurExceptionApplyRollbackFor(board, history);
        });
        assertThat(boardRepository.count()).isZero();
        assertThat(historyRepository.count()).isZero();
    }
}
