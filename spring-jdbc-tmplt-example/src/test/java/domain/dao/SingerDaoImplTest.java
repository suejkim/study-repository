package domain.dao;

import db.MariadbConnection;
import domain.model.Sex;
import domain.model.Singer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void get() throws Exception {
        commonDao.get(2L);
    }

    @Test
    void getAll() throws Exception {
        List<Singer> singers = commonDao.getAll();
        assertThat(singers.size()).isEqualTo(1);
    }

    @Test
    void countAll() throws Exception {
        int count = commonDao.countAll();
        List<Singer> singers = commonDao.getAll();
        assertThat(count).isEqualTo(singers.size());
//        assertThat(count).isEqualTo(1);
    }
}
