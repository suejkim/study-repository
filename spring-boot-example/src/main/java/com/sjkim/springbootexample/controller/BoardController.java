package com.sjkim.springbootexample.controller;

import com.sjkim.springbootexample.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity getBoardList() {
        return null;
    }

    @PostMapping("")
    public ResponseEntity addBoard() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBoard(@PathVariable long id) {
        return null;
    }

    @DeleteMapping("{/id}")
    public ResponseEntity deleteBoard(@PathVariable long id) {
        return null;
    }

}
