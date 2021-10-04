package com.sjkim.springbootexample.service;

import com.sjkim.springbootexample.persistence.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public boolean deleteBoard(long id) {
        var boardOptional = boardRepository.findById(id);
        boardOptional.ifPresent(boardRepository::delete);
        return true;
    }

    public boolean updateBoard(long id, String content) {
        var boardOptional = boardRepository.findById(id);
        if (boardOptional.isPresent()) {
            var board = boardOptional.get();
            board.updateContent(content);
            boardRepository.save(board);
        }
        return true;
    }
}
