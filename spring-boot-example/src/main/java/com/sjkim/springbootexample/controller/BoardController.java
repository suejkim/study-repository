package com.sjkim.springbootexample.controller;

import com.sjkim.springbootexample.dto.BoardSaveDto;
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
    public ResponseEntity getBoardList(@RequestParam String title,
                                       @RequestParam String content,
                                       @RequestParam String writer) {
        return null;
    }

    @PostMapping("")
    public ResponseEntity addBoard(@RequestBody BoardSaveDto saveDto) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBoard(@PathVariable long id, @RequestParam String content) {
        return null;
    }

    @DeleteMapping("{/id}")
    public ResponseEntity deleteBoard(@PathVariable long id) {
        return null;
    }

}
