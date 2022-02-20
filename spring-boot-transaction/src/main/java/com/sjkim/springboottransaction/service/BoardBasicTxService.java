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

    public boolean saveBoardAndHistory(Board board, History history) {
        boardRepository.save(board); // board 데이터 조회됨
        historyRepository.save(history); // history 데이터 조회됨
        return true;
    }

    @Transactional
    public boolean saveBoardAndHistoryWithTransactional(Board board, History history) {
        boardRepository.save(board);
        historyRepository.save(history);
        return true;
    } // method 호출이 완료된 후 커밋되어 데이터가 조회됨
}
