package com.sjkim.springbootexample.persistence;

import com.sjkim.springbootexample.domain.Board;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardCustomRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository {

    public BoardCustomRepositoryImpl() {
        super(Board.class);
    }

}
