package com.sjkim.springbootexample.persistence.board;

import com.querydsl.jpa.JPQLQuery;
import com.sjkim.springbootexample.domain.Board;

import com.sjkim.springbootexample.domain.QBoard;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardCustomRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository {

    private final QBoard board = QBoard.board;

    public BoardCustomRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public List<Board> findBySearchContent(String content) {
        JPQLQuery query = from(board)
                .where(board.content.contains(content));
        return query.fetch();
    }
}
