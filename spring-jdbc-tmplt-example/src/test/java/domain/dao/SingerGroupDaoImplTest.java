package domain.dao;

import db.MariadbConnection;
import domain.model.SingerGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;

class SingerGroupDaoImplTest {

    private CommonDao<SingerGroup> commonDao;

    @BeforeEach
    void setUp() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        MariadbConnection mariadbConnection = (MariadbConnection) context.getBean("mariadbConnection");
        commonDao = new SingerGroupDaoImpl(mariadbConnection);
    }

    @Test
    void add() throws Exception{
        SingerGroup singerGroup = SingerGroup.builder().name("뚜뚜루").debutDate(LocalDate.now()).agency("기획사").build();
        commonDao.add(singerGroup);
    }

    @Test
    void update() throws Exception {
        SingerGroup singerGroup = SingerGroup.builder()
                .id(1L)
                .debutDate(LocalDate.now().minusDays(1L))
                .name("뛰뛰루")
                .agency("기획사1")
                .build();
        commonDao.update(singerGroup);
    }

    @Test
    void delete() throws Exception {
        commonDao.delete(1L);
    }

    @Test
    void get() throws Exception {
        SingerGroup singerGroup = commonDao.get(2L);
        Assertions.assertEquals("기획사", singerGroup.getAgency());
    }
}