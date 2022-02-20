package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.model.Board;
import com.sjkim.springboottransaction.model.History;
import com.sjkim.springboottransaction.repository.BoardRepository;
import com.sjkim.springboottransaction.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class BoardErrorTxService {
    private final BoardRepository boardRepository;
    private final HistoryRepository historyRepository;

    TransactionAspectSupport transactionAspectSupport;

    @Transactional
    public boolean saveBoardOccurException(Board board) throws Exception{
        boardRepository.save(board);
        throw new Exception("Exception발생. commit");
    }

    @Transactional
    public boolean saveBoardOccurRuntimeException(Board board) throws Exception{
        boardRepository.save(board);
        throw new RuntimeException("RuntimeException발생. rollback");
    }


    public boolean save(Board board, History history) {
        boardRepository.save(board); // 데이터 저장
        historyRepository.save(history);  // 데이터 저장
        throw new RuntimeException("RuntimeException발생");
    } // 커밋됨

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
        throw new FileNotFoundException("Exception발생");
    } // 커밋됨

    @Transactional(rollbackFor = Exception.class)
    public boolean occurExceptionApplyRollbackFor(Board board, History history) throws Exception {
        boardRepository.save(board);
        historyRepository.save(history);
        throw new FileNotFoundException("Exception발생");
    } // 롤백 발생

    public boolean beforeSave(Board board, History history) {
        return this.saveWithTransactional(board, history);
    } // RuntimeException이라 롤백이 되어야하는데 커밋됨
}
