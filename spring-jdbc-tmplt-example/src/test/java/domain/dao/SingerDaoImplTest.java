package domain.dao;

import db.MariadbConnection;
import domain.model.Sex;
import domain.model.Singer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;

public class SingerDaoImplTest {

    private CommonDao<Singer> commonDao;

    @BeforeEach
    void setUp() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        MariadbConnection mariadbConnection = (MariadbConnection) context.getBean("mariadbConnection");
        commonDao = new SingerDaoImpl(mariadbConnection);
    }

    @Test
    void add() throws Exception {
        Singer singer = Singer.builder().name("김가수").birth(LocalDate.now()).sex(Sex.FEMALE).groupId(2).build();
        commonDao.add(singer);
    }

    @Test
    void update() throws Exception {
        Singer singer = Singer.builder().id(1).name("김가수").birth(LocalDate.now().minusDays(1)).sex(Sex.FEMALE).groupId(2).build();
        commonDao.update(singer);
    }

    @Test
    void delete() throws Exception {
        commonDao.delete(1);
    }
}
