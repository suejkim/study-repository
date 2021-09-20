package jpa_study.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_study");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        Board board = Board.builder()
                .title("TITLE")
                .content("CONTENT")
                .writer("WRITER")
                .build();

        entityManager.persist(board);

        board.changeTitle("UPDATED_TITLE");

        Board findBoard = entityManager.find(Board.class, 1L);
        System.out.println(">>>>> title: " + findBoard.getTitle());

        List<Board> findBoardList = entityManager.createQuery("select b from Board b", Board.class).getResultList();
        System.out.println(">>>>> list size: " + findBoardList.size());

        entityManager.close();
    }
}
