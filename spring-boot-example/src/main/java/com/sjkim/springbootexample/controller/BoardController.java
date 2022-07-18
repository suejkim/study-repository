package com.sjkim.springbootexample.controller;

import com.sjkim.springbootexample.dto.BoardSaveDto;
import com.sjkim.springbootexample.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity getBoardList(@RequestParam(required = false) String title,
                                       @RequestParam(required = false) String content,
                                       @RequestParam(required = false) String writer) {
        var boards = boardService.getBoardList(title, content, writer);
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity addBoard(@RequestBody BoardSaveDto saveDto) {
        var result = boardService.addBoard(saveDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBoard(@PathVariable long id, @RequestBody BoardSaveDto saveDto) {
        var result = boardService.updateBoard(id, saveDto.getContent());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable long id) {
        var result = boardService.deleteBoard(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
