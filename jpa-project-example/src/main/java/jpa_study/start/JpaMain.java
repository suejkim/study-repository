package jpa_study.start;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class JpaMain {
    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_study");
        // 엔티티 매니저 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // get 트랜잭션
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            logic(entityManager);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

    private static void logic(EntityManager entityManager) {
        // 비영속
        Board board = Board.builder()
                .title("TITLE")
                .content("CONTENT")
                .writer("WRITER")
                .build();

        // 영속
        entityManager.persist(board); // ERROR(detached entity passed to persist): 비영속인 상태에서 id를 추가하여 persist 할 경우 detached 로 인식
        Board findBoard = entityManager.find(Board.class, 1L);
        log.info("title: {}", findBoard.getTitle());

        board.changeTitle("UPDATED_TITLE");

        // 1차 캐시에서 조회
        findBoard = entityManager.find(Board.class, 1L);
        log.info("find title: {}", findBoard.getTitle());

        List<Board> findBoardList = entityManager.createQuery("select b from Board b", Board.class).getResultList();
        log.info(">>>>> list size {}", findBoardList.size());

        // 동일성 비교
        var board1 = entityManager.find(Board.class, 1L);
        var board2 = entityManager.find(Board.class, 1L);
        // 1차 캐시에 있는 같은 인스턴스를 반환
        log.info("==? {}", board1 == board2);
        log.info("equals {}", board1.equals(board2));

        // 삭제
        entityManager.remove(board);
    }
}
