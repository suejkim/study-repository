package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.model.Board;
import com.sjkim.springboottransaction.model.History;
import com.sjkim.springboottransaction.repository.BoardRepository;
import com.sjkim.springboottransaction.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardBasicTxService {
    private final BoardRepository boardRepository;
    private final HistoryRepository historyRepository;

    public boolean saveBoard(Board board, History history) {
        boardRepository.save(board);
        historyRepository.save(history);

        return true;
    }

    @Transactional
    public boolean saveBoardWithTransactional(Board board, History history) {
        boardRepository.save(board);
        historyRepository.save(history);

        return true;
    }
}
