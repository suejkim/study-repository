package com.sjkim.springboottransaction.service;

import com.sjkim.springboottransaction.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardIsolationServiceTest {

    @Autowired
    private BoardIsolationService boardIsolationService;
    @Autowired
    private InitData initData;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("Isolation.DEFAULT")
    void isolationDefaultTest() {
        var board = initData.buildBoard();
        boardIsolationService.saveBoard(board);

        boardIsolationService.getBoardIsolationDefault(1L); // 트랜잭션
        board = boardRepository.findById(1L).get(); // 별도의 트랜잭션

        // 첫번째 트랜잭션 도중에 두번째 트랜잭션으로 데이터를 변경했지만 조회되지 않고 첫번째 트랜잭션이 완료되면 조회됨
    }

    @Test
    @DisplayName("Isolation.READ_UNCOMMITTED. 두 트랜잭션에서 update가 일어나는 경우. 그리고 별도의 트랜잭션에서 롤백함")
    void isolationReadUncommittedTest() {
        var board = initData.buildBoard();
        boardIsolationService.saveBoard(board);

        System.out.println("======= 트랜잭션 발생");
        boardIsolationService.getBoardIsolationReadUncommitted(1L); // 트랜잭션
        System.out.println("======= 트랜잭션 종료");

        board = boardRepository.findById(1L).get(); // 별도의 트랜잭션
        System.out.println(">>>> " + board.getTitle());
        System.out.println(">>>> " + board.getWriter());

        // 별도의 트랜잭션에서 롤백을 했는데도 업데이트가 발생
        // Hibernate:
        //    update
        //        board
        //    set
        //        content=?,
        //        created_at=?,
        //        title=?,
        //        updated_at=?,
        //        writer=?
        //    where
        //        id=?
        // title만 update하면 되는데 이미 엔티티가 가지고 있는 writer 까지 업데이트 됨 => @DynamicUpdate 추가

        // 추가하면
        // Hibernate:
        //    update
        //        board
        //    set
        //        title=?,
        //        updated_at=?
        //    where
        //        id=?
    }

    @Test
    @DisplayName("Isolation.READ_COMMITTED. 두 트랜잭션에서 update가 일어나는 경우. 그리고 별도의 트랜잭션에서 롤백함")
    void isolationReadCommittedTest() {
        var board = initData.buildBoard();
        boardIsolationService.saveBoard(board);

        System.out.println("======= 트랜잭션 발생");
        boardIsolationService.getBoardIsolationReadCommitted(1L); // 트랜잭션
        System.out.println("======= 트랜잭션 종료");

        board = boardRepository.findById(1L).get(); // 별도의 트랜잭션
        System.out.println(">>>> " + board.getTitle());
        System.out.println(">>>> " + board.getWriter());
    } // Commit 된 것만 읽으므로 롤백되므로 WRITER2가 업데이트 되지 않는다.

    @Test
    @DisplayName("Isolation.READ_COMMITTED")
    void isolationReadCommittedOnlyGetTest() {
        var board = initData.buildBoard();
        boardIsolationService.saveBoard(board);

        System.out.println("======= 트랜잭션 발생");
        boardIsolationService.getBoardIsolationReadCommittedOnlyGet(1L); // 트랜잭션
        System.out.println("======= 트랜잭션 종료");
        board = boardRepository.findById(1L).get(); // 별도의 트랜잭션
        System.out.println(">>>> " + board.getWriter());
    }

    @Test
    @DisplayName("Isolation.REPEATABLE_READ")
    void getBoardIsolationRepeatableReadTest() {
        var board = initData.buildBoard();
        boardIsolationService.saveBoard(board);

        System.out.println("======= 트랜잭션 발생");
        boardIsolationService.getBoardIsolationRepeatableRead(1L); // 트랜잭션
        System.out.println("======= 트랜잭션 종료");
        board = boardRepository.findById(1L).get(); // 별도의 트랜잭션
        System.out.println(">>>> " + board.getWriter());
    }
}
