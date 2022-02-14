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
public class BoardErrorTxService {
    private final BoardRepository boardRepository;
    private final HistoryRepository historyRepository;

    public boolean save(Board board, History history) {
        boardRepository.save(board); // 데이터 저장
        historyRepository.save(history);

        throw new RuntimeException("RuntimeException발생");
    } // 롤백 발생하지 않음

    @Transactional
    public boolean saveWithTransactional(Board board, History history) {
        boardRepository.save(board);
        historyRepository.save(history);

        throw new RuntimeException("RuntimeException발생");
    } // 롤백 발생

}
