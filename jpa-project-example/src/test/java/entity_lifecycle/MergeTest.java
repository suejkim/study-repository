package entity_lifecycle;

import jpa_study.start.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Slf4j
class MergeTest {
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa_study");
    }

    private Board mergeForDetached() {
        var entityManager = entityManagerFactory.createEntityManager();
        var entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        var board = Board.builder()
                .title("TITLE")
                .content("CONTENT")
                .writer("WRITER")
                .build();
        entityManager.persist(board);
        entityTransaction.commit();
        entityManager.close(); // 준영속상태
        return board;
    }

    private Board mergeForManaged(Board board) {
        var entityManager = entityManagerFactory.createEntityManager();
        var entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        var mergedBoard = entityManager.merge(board); // 영속상태로 변경
        log.info("board isContains {}, {}", entityManager.contains(board), board.getTitle());
        log.info("mergedBoard isContains {}, {}", entityManager.contains(mergedBoard), board.getTitle()); // 다른 board로써 새로운 영속상태의 entity
        entityTransaction.commit(); // dirtyCheck
        entityManager.close();
        return mergedBoard;
    }

    @Test
    void mergeTest() {
        var board = mergeForDetached();
        board.changeTitle("UPDATE_TITLE");
        mergeForManaged(board);
    }
}
