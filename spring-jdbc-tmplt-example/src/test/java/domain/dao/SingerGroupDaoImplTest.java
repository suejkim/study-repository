package domain.dao;

import db.MariadbConnection;
import domain.model.SingerGroup;
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
}