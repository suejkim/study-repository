package com.sjkim.springbootexample.persistence.board;

import com.sjkim.springbootexample.domain.Board;

import java.util.List;

public interface BoardCustomRepository {

    List<Board> findBySearchContent(String content);
}
