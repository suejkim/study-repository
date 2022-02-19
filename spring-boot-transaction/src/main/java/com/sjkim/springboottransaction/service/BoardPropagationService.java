package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.model.Board;
import com.sjkim.springboottransaction.model.History;
import com.sjkim.springboottransaction.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardPropagationService {

    private final BoardRepository boardRepository;
    private final HistoryPropagationService historyPropagationService;

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    // 트랜잭션이 잡혀있으면 사용하고 없으면 트랜잭션 만들어서 사용. save() 이 그 예시
    @Transactional
    public boolean savePropagationRequired(Board board, History history) {
        saveBoard(board);
        historyPropagationService.saveHistoryPropagationRequired(history);
        return true;
    }

    // 호출하는 쪽과 상관없이(부모 트랜잭션 상관없이) 트랜잭션 있든 없든 무조건 자체적으로 독립적인 트랜잭션 생성
    @Transactional
    public boolean savePropagationRequiresNew(Board board, History history) {
        saveBoard(board);
        try {
            historyPropagationService.saveHistoryPropagationRequiresNew(history);
        } catch (Exception e) {
            //
        }
        return true;
    }

    // 부모트랜잭션 반드시 있어야하고 없으면 오류발생
    public boolean savePropagationMandatory(Board board, History history) {
        saveBoard(board);
        historyPropagationService.saveHistoryPropagationMandatory(history);
        return true;
    }

    // 부모트랜잭션 없어야하고 존재시 오류발생
    @Transactional
    public boolean savePropagationNever(Board board, History history) {
        saveBoard(board);
        historyPropagationService.saveHistoryPropagationNever(history);
        return true;
    }

    //종속적이지만 상위 트랜잭션에 영향을 주지 않는다.
    public Board savePropagationNested(Board board, History history) {
        return null;
    }

    public Board savePropagationNotSupported(Board board, History history) {
        return null;
    }

    // REQUIRED와 비슷하지만 없으면 굳이 트랜잭션 만들지 않음
    public Board savePropagationSupports(Board board, History history) {
        return null;
    }
}
