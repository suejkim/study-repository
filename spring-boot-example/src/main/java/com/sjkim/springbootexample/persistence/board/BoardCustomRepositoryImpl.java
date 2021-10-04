package com.sjkim.springbootexample.persistence.board;

import com.google.common.base.Strings;
import com.querydsl.core.types.dsl.BooleanExpression;
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

    @Override
    public List<Board> findBySearchTitleAndContentAndWriter(String title, String content, String writer) {
        JPQLQuery<Board> query = from(board)
                .where(likeTitle(title), likeContent(content), likeWriter(writer))
                .orderBy(board.id.desc());
        return query.fetch();
    }

    private BooleanExpression likeTitle(String title) {
        if (Strings.isNullOrEmpty(title)) {
            return null;
        }
        return board.title.contains(title);
    }

    private BooleanExpression likeContent(String content) {
        if (Strings.isNullOrEmpty(content)) {
            return null;
        }
        return board.content.contains(content);
    }

    private BooleanExpression likeWriter(String writer) {
        if (Strings.isNullOrEmpty(writer)) {
            return null;
        }
        return board.writer.contains(writer);
    }
}
