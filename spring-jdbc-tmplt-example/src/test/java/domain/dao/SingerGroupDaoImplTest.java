package domain.dao;

import db.MariadbConnection;
import domain.model.SingerGroup;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;

class SingerGroupDaoImplTest { // TODO TestCode 재작성

    private CommonDao<SingerGroup> singerGroupDao;

    @BeforeEach
    void setUp() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        MariadbConnection mariadbConnection = (MariadbConnection) context.getBean("mariadbConnection");
        singerGroupDao = new SingerGroupDaoImpl(mariadbConnection);
    }

    private SingerGroup setSingerGroup() {
        return SingerGroup.builder().name("SINGER_GROUP").debutDate(LocalDate.now()).agency("AGENCY").build();
    }

    @Test
    void add() throws Exception{
        boolean result = singerGroupDao.add(setSingerGroup());
        MatcherAssert.assertThat(result, is(Boolean.TRUE));
    }

    @Test
    void update() throws Exception {
        SingerGroup singerGroup = SingerGroup.builder()
                .id(1L)
                .debutDate(LocalDate.now().minusDays(1L))
                .name("뛰뛰루")
                .agency("기획사1")
                .build();
        singerGroupDao.update(singerGroup);
    }

    @Test
    void delete() throws Exception {
        singerGroupDao.delete(1L);
    }

    @Test
    void get() throws Exception {
        SingerGroup singerGroup = singerGroupDao.get(2L);
        Assertions.assertEquals("기획사", singerGroup.getAgency());
    }

    @Test
    void getAll() throws Exception {
        List<SingerGroup> singerGroups = singerGroupDao.getAll();
        org.assertj.core.api.Assertions.assertThat(singerGroups.size()).isEqualTo(2);
    }

    @Test
    void countAll() throws Exception {
        int count = singerGroupDao.countAll();
        org.assertj.core.api.Assertions.assertThat(count).isEqualTo(2);
    }
}