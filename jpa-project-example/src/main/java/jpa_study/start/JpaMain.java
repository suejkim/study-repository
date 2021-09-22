package jpa_study.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
        entityManager.persist(board);

        board.changeTitle("UPDATED_TITLE");

        Board findBoard = entityManager.find(Board.class, 1L);
        System.out.println(">>>>> title: " + findBoard.getTitle());

        List<Board> findBoardList = entityManager.createQuery("select b from Board b", Board.class).getResultList();
        System.out.println(">>>>> list size: " + findBoardList.size());

        // 삭제
        entityManager.remove(board);

    }
}
