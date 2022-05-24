package entity_lifecycle;

import jpa_study.start.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class EntityLifeCycleTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @BeforeEach
    void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa_study");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    @AfterEach
    void tearDown(TestInfo info) {
        var tags = info.getTags();
        boolean containTag = tags.contains("skipAfterEach");
        if (containTag) {
            log.info("skip @AfterEach");
        } else {
            log.info("em, emf close");
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Test
    @DisplayName("비영속")
    @Tag("skipAfterEach")
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
    @Tag("skipAfterEach")
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
        Board resultBoard = entityManager.find(Board.class, 2001L); // NOTE: data.sql 마지막 레코드에 저장된 id가 2000번이므로
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
        Board resultBoard = entityManager.find(Board.class, 2001L);
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

    @Test
    @DisplayName("준영속. clear()")
    void detachedState_1() {
        entityTransaction.begin();
        Board resultBoard = entityManager.find(Board.class, 1000L);
        entityManager.clear();
        resultBoard.changeTitle("UPDATE");
        var resultAfterClear = entityManager.contains(resultBoard);
        assertThat(resultAfterClear).isFalse();
        entityTransaction.commit();
    }

    @Test
    @DisplayName("준영속. detached()")
    void detachedState_2() { // TODO 재확인 필요
        entityTransaction.begin();
        Board board = Board.builder()
                .title("TITLE")
                .content("CONTENT")
                .writer("WRITER")
                .build();

        entityManager.persist(board); // managed.
//        var resultAfterPersist = entityManager.contains(board);
//        assertThat(resultAfterPersist).isTrue();

        entityManager.detach(board); // detached
//        var resultAfterDetach = entityManager.contains(board);
//        assertThat(resultAfterDetach).isFalse();

        entityTransaction.commit();
    }

    @Test
    @DisplayName("준영속. close()")
    @Tag("skipAfterEach")
    void detachedState_3() {
        Board resultBoard = entityManager.find(Board.class, 1000L);
        entityManager.close(); // Session/EntityManager is closed
        var resultAfterClose = entityManager.isOpen();
        assertThat(resultAfterClose).isFalse();
    }

    @Test
    @DisplayName("준영속. 변경감지 되지 않음")
    void detachedState_4() {
        entityTransaction.begin();
        Board resultBoard = entityManager.find(Board.class, 1000L);
        resultBoard.changeTitle("UDPATE");
        entityManager.detach(resultBoard); // detached

        var resultAfterDetach = entityManager.contains(resultBoard);
        assertThat(resultAfterDetach).isFalse();
        entityTransaction.commit();
    }
}