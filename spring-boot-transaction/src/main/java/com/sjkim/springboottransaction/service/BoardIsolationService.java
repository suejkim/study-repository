package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.model.Board;
import com.sjkim.springboottransaction.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardIsolationService {

    private final BoardRepository boardRepository;

    public boolean saveBoard(Board board) {
        board = boardRepository.save(board);
        return true;
    }

    @Transactional(isolation = Isolation.DEFAULT)
    public Board getBoard(Long id) {
        var board = boardRepository.findById(id).get();
        System.out.println(board.getTitle());

        board = boardRepository.findById(id).get();
        System.out.println(board.getTitle());
        return board;
    }
}
