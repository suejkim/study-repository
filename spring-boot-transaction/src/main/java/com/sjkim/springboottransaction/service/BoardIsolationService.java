package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.model.Board;
import com.sjkim.springboottransaction.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class BoardIsolationService {

    private final BoardRepository boardRepository;
    private final EntityManager entityManager;

    public boolean saveBoard(Board board) {
        board = boardRepository.save(board);
        return true;
    }

    @Transactional(isolation = Isolation.DEFAULT)
    public Board getBoardIsolationDefault(Long id) {
        var board = boardRepository.findById(id).get();
        System.out.println(">>>>>>>>>>>>>> (1) " + board.getWriter());

        // start transaction. 중간에 update 일어날 경우

        board = boardRepository.findById(id).get();
        System.out.println(">>>>>>>>>>>>>> (2) " + board.getWriter());

        // commit
        return board;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Board getBoardIsolationReadUncommitted(Long id) {
        // start transaction. 중간에 update 일어날 경우 (조회전에 트랜잭션 발생시키도록 한다)
        System.out.println("======= 또다른 트랜잭션 발생");
        var board = boardRepository.findById(id).get(); // WRITER2 read -> dirty read
        System.out.println(">>>> " + board.getWriter());

        // update title
        board.setTitle("TITLE2");
        board = boardRepository.findById(id).get();
        boardRepository.save(board);
        System.out.println(">>>> " + board.getTitle());
        System.out.println(">>>> " + board.getWriter());

        System.out.println("======= 롤백합니다.");
        return board;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Board getBoardIsolationReadCommitted(Long id) {
        // start transaction. 중간에 update 일어날 경우 (조회전에 트랜잭션 발생시키도록 한다)
        System.out.println("======= 또다른 트랜잭션 발생");
        var board = boardRepository.findById(id).get();
        System.out.println(">>>> " + board.getWriter());

        // update title
        board.setTitle("TITLE2");
        board = boardRepository.findById(id).get();
        boardRepository.save(board);
        System.out.println(">>>> " + board.getTitle());
        System.out.println(">>>> " + board.getWriter());

        System.out.println("======= 롤백합니다.");
        return board;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Board getBoardIsolationReadCommittedOnlyGet(Long id) {
        // start transaction. 중간에 update 일어날 경우
        System.out.println("======= 또다른 트랜잭션 발생");
        var board = boardRepository.findById(id).get();
        System.out.println(">>>> " + board.getWriter());

        entityManager.clear(); // 캐시 때문에 새롭게 업데이트 된 값을 조회하지 못하므로 추가. 추가하게 되면 unRepeatableRead 현상이 발생한다.

        System.out.println("======= 커밋합니다.");

        board = boardRepository.findById(id).get(); // Entity Cache (findById)
        System.out.println(">>>> " + board.getWriter()); // 커밋했지만 WRITER 그대로임

        return board;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Board getBoardIsolationRepeatableRead(Long id) {
        System.out.println("======= 또다른 트랜잭션 발생");

        var board = boardRepository.findById(id).get();
        System.out.println(">>>>>>>>>>>>>> (1) " + board.getWriter());

        entityManager.clear();
        System.out.println("======= 커밋합니다.");

        board = boardRepository.findById(id).get();
        System.out.println(">>>>>>>>>>>>>> (2) " + board.getWriter()); // entityManager로 영속성 context를 clear 해도 WRITER 값이 조회됨

        return board;
    }
}
