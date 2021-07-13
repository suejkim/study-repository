package domain.dao;

import db.MariadbConnection;
import domain.model.Sex;
import domain.model.Singer;
import domain.model.SingerGroup;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import util.QueryExecutor;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SingerDaoImplTest {

    private CommonDao<Singer> singerDao;
    private CommonDao<SingerGroup> singerGroupDao;

    @BeforeEach
    void beforeEach() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        MariadbConnection mariadbConnection = (MariadbConnection) context.getBean("mariadbConnection");
        QueryExecutor queryExecutor = new QueryExecutor(mariadbConnection);
        singerDao = new SingerDaoImpl(queryExecutor);
        singerGroupDao = new SingerGroupDaoImpl(mariadbConnection);
    }

    private SingerGroup setSingerGroup() {
        return SingerGroup.builder().name("SINGER_GROUP").debutDate(LocalDate.now()).agency("AGENCY").build();
    }

    private Singer setSinger() throws Exception {
        List<SingerGroup> singerGroups = singerGroupDao.getAll();
        if (singerGroups.isEmpty()) {
            singerGroups.add(setSingerGroup());
            singerGroups = singerGroupDao.getAll();
        }
        long id = singerGroups.get(0).getId();
        return Singer.builder().name("SINGER").birth(LocalDate.now()).sex(Sex.FEMALE).groupId(id).build();
    }

    private List<Singer> getSingerList() throws Exception {
        List<Singer> singers = singerDao.getAll();
        if (singers.isEmpty()) {
            Singer singer = setSinger();
            singerDao.add(singer);
            singers = singerDao.getAll();
        }
        return singers;
    }

    @Test
    void addSinger() throws Exception {
        boolean result = singerDao.add(setSinger());
        MatcherAssert.assertThat(result, is(Boolean.TRUE));
    }

    @Test
    void updateSinger() throws Exception {
        LocalDate birth = LocalDate.now().plusDays(1L);
        List<Singer> singers = getSingerList();
        Singer singer = singers.get(0);
        singer.setBirth(birth);
        boolean result = singerDao.update(singer);
        assertEquals(Boolean.TRUE, result);
        Singer updatedSinger = singerDao.get(singer.getId());
        MatcherAssert.assertThat(singer.getId(), equalTo(updatedSinger.getId()));
    }

    @Test
    void deleteSinger() throws Exception {
        List<Singer> singers = getSingerList();
        long id = singers.get(0).getId();
        boolean result = singerDao.delete(id);
        assertEquals(Boolean.TRUE, result);
        Singer deletedSinger = singerDao.get(id);
        assertNull(deletedSinger);
        MatcherAssert.assertThat(deletedSinger, nullValue());
    }

    @Test
    void getSinger() throws Exception {
        List<Singer> singers = getSingerList();
        long id = singers.get(0).getId();
        Singer singer = singerDao.get(id);
        Assertions.assertEquals(id, singer.getId());
    }

    @Test
    void getAllSinger() throws Exception {
        List<Singer> singers = singerDao.getAll();
        int count = singerDao.countAll();
        MatcherAssert.assertThat(singers.size(), equalTo(count));
    }

    @Test
    void countAll() throws Exception {
        int count = singerDao.countAll();
        List<Singer> singers = singerDao.getAll();
        assertThat(count).isEqualTo(singers.size());
    }
}
