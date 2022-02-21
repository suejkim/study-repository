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
    private final BoardBasicOtherTxService basicOtherTxService;

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

    // 같은 클래스 내에서 호출
    public boolean beforeSaveAndUpdateInSameClass(Board board, History history) {
        return this.saveAndUpdateWithTransactional(board, history);
    }

    @Transactional
    public boolean saveAndUpdateWithTransactional(Board board, History history) {
        boardRepository.save(board);
        historyRepository.save(history);
        board.setTitle("TITLE2"); // update 되지 않음
        return true;
    } // 아래처럼 다른 클래스로 분리한다.

    public boolean beforeSaveAndUpdate(Board board, History history) {
        return basicOtherTxService.saveAndUpdateWithTransactional(board, history);
    }
}
