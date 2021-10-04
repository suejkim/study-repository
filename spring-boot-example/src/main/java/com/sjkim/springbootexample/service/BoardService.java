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
}
