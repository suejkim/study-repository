package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.repository.BoardRepository;
import com.sjkim.springboottransaction.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.IllegalTransactionStateException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BoardPropagationServiceTest {

    @Autowired
    private BoardPropagationService boardPropagationService;
    @Autowired
    private InitData initData;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private HistoryRepository historyRepository;

    @Test
    void savePropagationRequired() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(RuntimeException.class, () -> {
            boardPropagationService.savePropagationRequired(board, history);
        });
        assertThat(boardRepository.count()).isZero();
        assertThat(historyRepository.count()).isZero();
    }

    @Test
    void savePropagationRequiresNew() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardPropagationService.savePropagationRequiresNew(board, history);
        assertThat(boardRepository.count()).isEqualTo(1);
        assertThat(historyRepository.count()).isZero();
    }

    @Test
    void savePropagationMandatory() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(IllegalTransactionStateException.class, () -> {
            boardPropagationService.savePropagationMandatory(board, history);
        });
        // No existing transaction found for transaction marked with propagation 'mandatory'
    }

    @Test
    void savePropagationNever() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(IllegalTransactionStateException.class, () -> {
            boardPropagationService.savePropagationNever(board, history);
        });
        // Existing transaction found for transaction marked with propagation 'never'
    }

    @Test
    void savePropagationNested() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        boardPropagationService.savePropagationNested(board, history);
        assertThat(boardRepository.count()).isEqualTo(1L);
        assertThat(historyRepository.count()).isZero();
    }

    @Test
    void savePropagationNested_2() {
        var board = initData.buildBoard();
        var history = initData.buildHistory();
        assertThrows(RuntimeException.class, () -> {
            boardPropagationService.savePropagationNestedOccurException(board, history);
        });
        assertThat(boardRepository.count()).isZero();
        assertThat(historyRepository.count()).isZero();
    }
}