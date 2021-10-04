package com.sjkim.springbootexample.service;

import com.sjkim.springbootexample.domain.Board;
import com.sjkim.springbootexample.dto.BoardDto;
import com.sjkim.springbootexample.dto.BoardSaveDto;
import com.sjkim.springbootexample.persistence.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public boolean addBoard(BoardSaveDto saveDto) {
        Board board = Board.builder()
                .title(saveDto.getTitle())
                .content(saveDto.getContent())
                .writer(saveDto.getWriter()).build();
        boardRepository.save(board);
        return true;
    }

    public List<BoardDto> getBoardList(String title, String content, String writer) {
        var boardList = boardRepository.findBySearchTitleAndContentAndWriter(title, content, writer);
        return boardList.stream().map(board -> BoardDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt()).build()).collect(Collectors.toList());
    }
}
