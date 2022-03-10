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
        boardRepository.findById(1L).ifPresent(targetBoard -> assertThat(targetBoard.getTitle()).isEqualTo("TITLE"));
    }

    @Test
    @DisplayName("다른 클래스 내 @Transactional 적용된 메소드 호출. update 시")
    void beforeSaveAndUpdate() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardBasicTxService.beforeSaveAndUpdate(board, history);
        boardRepository.findById(1L).ifPresent(targetBoard -> assertThat(targetBoard.getTitle()).isEqualTo("TITLE2"));
    }

    @Test
    @DisplayName("다른 클래스 내 @Transactional 적용된 메소드 호출. RuntimeException 발생")
    void beforeSaveAndUpdateOccurException() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(RuntimeException.class, () -> {
            boardBasicTxService.beforeSaveAndUpdateOccurException(board, history);
        });
        var targetBoard = boardRepository.findById(1L).orElse(null);
        assertThat(targetBoard).isNull();
        var targetHistory = historyRepository.findById(1L).orElse(null);
        assertThat(targetHistory).isNull();
    }

    @Test
    @DisplayName("다른 클래스 내 @Transactional 적용된 메소드 호출. RuntimeException 발생. 예외처리 수행")
    void beforeSaveAndUpdateTryCatch() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(Exception.class, () -> {
            boardBasicTxService.beforeSaveAndUpdateTryCatch(board, history);
        });
        var targetBoard = boardRepository.findById(1L).orElse(null);
        assertThat(targetBoard).isNull();
        var targetHistory = historyRepository.findById(1L).orElse(null);
        assertThat(targetHistory).isNull();
    }

    @Test
    @DisplayName("다른 클래스 내 @Transactional 적용되지 않은 메소드 호출. RuntimeException 발생.")
    void beforeSaveAndUpdateWithTransactional() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(Exception.class, () -> {
            boardBasicTxService.beforeSaveAndUpdateWithTransactional(board, history);
        });
        var targetBoard = boardRepository.findById(1L).orElse(null);
        assertThat(targetBoard).isNull();
        var targetHistory = historyRepository.findById(1L).orElse(null);
        assertThat(targetHistory).isNull();
    } // 한 트랜잭션내에서 RuntimeException 발생하였으므로 롤백

    @Test
    @DisplayName("다른 클래스 내 @Transactional 적용된 메소드 호출. RuntimeException 발생.")
    void beforeSaveAndUpdateWithoutTransactional() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(Exception.class, () -> {
            boardBasicTxService.beforeSaveAndUpdateWithoutTransactional(board, history);
        });
        var targetBoard = boardRepository.findById(1L).orElse(null);
        assertThat(targetBoard.getTitle()).isEqualTo("TITLE");
        var targetHistory = historyRepository.findById(1L).orElse(null);
        assertThat(targetHistory).isNull();
    }
}