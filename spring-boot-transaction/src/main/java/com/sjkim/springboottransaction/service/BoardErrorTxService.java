package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.model.Board;
import com.sjkim.springboottransaction.model.History;
import com.sjkim.springboottransaction.repository.BoardRepository;
import com.sjkim.springboottransaction.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@RequiredArgsConstructor
public class BoardErrorTxService {
    private final BoardRepository boardRepository;
    private final HistoryRepository historyRepository;

    TransactionAspectSupport transactionAspectSupport;

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


    @Transactional
    public boolean occurException(Board board, History history) throws Exception {
        boardRepository.save(board);
        historyRepository.save(history);
        throw new Exception("Exception발생");
    } // 커밋됨

    @Transactional(rollbackFor = Exception.class)
    public boolean occurExceptionApplyRollbackFor(Board board, History history) throws Exception {
        boardRepository.save(board);
        historyRepository.save(history);
        throw new Exception("Exception발생");
    } // 롤백 발생
}
