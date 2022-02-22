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

    // 다른 클래스로 분리
    // RuntimeException 발생 -> board, history 모두 롤백
    @Transactional
    public boolean beforeSaveAndUpdateOccurException(Board board, History history) {
        boardRepository.save(board);
        return basicOtherTxService.saveAndUpdateWithTransactionalOccurException(board, history);
    }

    @Transactional
    public boolean beforeSaveAndUpdateTryCatch(Board board, History history) {
        boardRepository.save(board);
        try {
            basicOtherTxService.saveAndUpdateWithTransactionalOccurException(board, history);
        } catch (Exception e) {
            //
        }
        // Transaction silently rolled back because it has been marked as rollback-onlyorg.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only
        // try-catch 문으로 예외처리를 해도 RuntimeException은 rollback 여부가 true이므로 (롤백마크)
        // 최종커밋시 rollback-only mark 때문에 롤백이 됨
        return true;
    }
}
