package entity_lifecycle;

import jpa_study.start.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class EntityLifeCycleTest {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @BeforeEach
    void setup() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_study");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    @Test
    @DisplayName("비영속")
    void newState() {
        entityTransaction.begin();
        Board board = Board.builder()
                .title("TITLE")
                .content("CONTENT")
                .writer("WRITER")
                .build();
        var result = entityManager.contains(board);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("영속. 커밋하지 않을 경우")
    void managedStateNotCommit() {
        String title = "TITLE";
        entityTransaction.begin();
        Board board = Board.builder()
                .title(title)
                .content("CONTENT")
                .writer("WRITER")
                .build();
        entityManager.persist(board); // managed.
        var result = entityManager.contains(board);
        assertThat(result).isTrue();

        // 영속 엔티티 조회
        Board resultBoard = entityManager.find(Board.class, 1L);
        assertThat(resultBoard.getTitle()).isEqualTo(title);
        // commit 하지 않아서 DB에는 데이터 없는 상태
    }

    @Test
    @DisplayName("영속. 커밋할 경우")
    void managedStateCommit() {
        String title = "TITLE";
        entityTransaction.begin();
        Board board = Board.builder()
                .title(title)
                .content("CONTENT")
                .writer("WRITER")
                .build();
        entityManager.persist(board); // managed.
        var result = entityManager.contains(board);
        assertThat(result).isTrue();

        // 영속 엔티티 조회
        Board resultBoard = entityManager.find(Board.class, 1L);
        assertThat(resultBoard.getTitle()).isEqualTo(title);

        // commit -> DB 저장
        entityTransaction.commit();
    }


    @Test
    @DisplayName("변경감지")
    void dirtyChecking() {
//        entityTransaction.begin();
        Board resultBoard = entityManager.find(Board.class, 1000L); // 스냅샷 & 캐싱
        assertThat(resultBoard.getTitle()).isEqualTo("1000_TITLE");

        String changedTitle = "UPDATED_TITLE";
        resultBoard.changeTitle(changedTitle);

        Board resultBoard2 = entityManager.find(Board.class, 1000L); // 1차 캐시에 들어있는 엔티티
        assertThat(resultBoard2.getTitle()).isEqualTo(changedTitle);

        log.info(">>>> tx begin");
        entityTransaction.begin();
        log.info(">>>> tx commit");
        entityTransaction.commit(); // 기본은 entity 모든 필드 업데이트. @DynamicUpdate로 동적 쿼리 실행: 변경 감지된 필드만 업데이트 된다.
    }
}