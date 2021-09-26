package com.sjkim.springbootexample.persistence.board;

import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;
import com.sjkim.springbootexample.domain.Board;

import com.sjkim.springbootexample.domain.QBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board>, BoardCustomRepository{

    List<Board> findByIdGreaterThan(Long id, Pageable pageable);

    // @Query Test
    @Query("select b from Board b where b.title like %?1% order by b.id desc")
    List<Board> findByTitle(String title);

    @Query("select b from Board b where b.content like %:contentParam% order by b.id desc")
    List<Board> findByContent(@Param("contentParam") String content);

    @Query("select b from #{#entityName} b where b.writer = ?1 order by b.id desc")
    List<Board> findByWriter(String writer);

    @Query(value = "select b.title, b.content from Board b where b.writer like %?1% order by b.id desc",
            nativeQuery = true)
    List<Object[]> findTitleAndContentByWriter(String writer, Pageable pageable);

    default Predicate findBySearchTitle(String title) {
        QBoard board = QBoard.board;
        if (Strings.isNullOrEmpty(title)) {
            return null;
        }
        return board.title.eq(title);
    }
}
