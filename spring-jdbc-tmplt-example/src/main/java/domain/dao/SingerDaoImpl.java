package domain.dao;

import db.ConnectionFactory;
import domain.model.Sex;
import domain.model.Singer;
import lombok.extern.slf4j.Slf4j;
import util.PreparedStatementProcessor;
import util.ResultSetConverter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SingerDaoImpl implements CommonDao<Singer> {

    private final ConnectionFactory connectionFactory;

    public SingerDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public boolean add(Singer singer) throws Exception {
        String sql = "INSERT INTO singer(name, birth, sex, group_id) VALUES(?, ?, ?, ?)";
        return execute(sql, new PreparedStatementProcessor() {
            @Override
            public void setPreparedStatement(PreparedStatement psm) throws Exception {
                psm.setString(1, singer.getName());
                psm.setDate(2, Date.valueOf(singer.getBirth()));
                psm.setString(3, singer.getSex().name());
                psm.setLong(4, singer.getGroupId());
            }
        });
    }

    @Override
    public boolean update(Singer singer) throws Exception {
        String sql = "update singer set name = ?, birth = ?, sex = ?, group_id = ? where id = ?";
        return execute(sql, psm -> {
            psm.setString(1, singer.getName());
            psm.setDate(2, Date.valueOf(singer.getBirth()));
            psm.setString(3, singer.getSex().name());
            psm.setLong(4, singer.getGroupId());
            psm.setLong(5, singer.getId());
        });
    }

    @Override
    public boolean delete(long id) throws Exception {
        String sql = "delete from singer where id = ?";
        return execute(sql, psm -> {
            psm.setLong(1, id);
        });
    }

    @Override
    public Singer get(long id) throws Exception {
        String sql = "select * from singer where id = ?";
        return execute(sql, psm -> psm.setLong(1, id), rs -> setSinger(rs));
    }

    @Override
    public List<Singer> getAll() throws Exception {
        String sql = "select * from singer";
        return execute(sql,
                new PreparedStatementProcessor() {
                    @Override
                    public void setPreparedStatement(PreparedStatement psm) {

                    }
                },
                new ResultSetConverter<List<Singer>>() {
                    @Override
                    public List<Singer> convertResultSetToModel(ResultSet rs) throws Exception {
                        return setSingerList(rs);
                    }
                }
        );
    }

    @Override
    public int countAll() throws Exception {
        String sql = "select count(*) from singer";
        return execute(sql, new PreparedStatementProcessor() {
            @Override
            public void setPreparedStatement(PreparedStatement psm) throws Exception {

            }
        }, new ResultSetConverter<Integer>() {
            @Override
            public Integer convertResultSetToModel(ResultSet rs) throws Exception {
                return setCountAll(rs);
            }
        });
    }

    private boolean execute(String sql, PreparedStatementProcessor processor) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        processor.setPreparedStatement(psm);
        psm.execute();
        psm.close();
        connectionFactory.close();
        return true;
    }

    private <T> T execute(String sql, PreparedStatementProcessor processor, ResultSetConverter<T> converter) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        processor.setPreparedStatement(psm);
        ResultSet rs = psm.executeQuery();
        T t = converter.convertResultSetToModel(rs);
        rs.close();
        psm.close();
        connectionFactory.close();
        return t;
    }


    private Singer setModelFromResultSet(ResultSet rs) throws Exception {
        Singer singer = new Singer();
        singer.setId(rs.getLong("id"));
        singer.setName(rs.getString("name"));
        singer.setBirth(rs.getDate("birth").toLocalDate());
        singer.setSex(Sex.valueOf(rs.getString("sex")));
        singer.setGroupId(rs.getLong("group_id"));
        return singer;
    }

    private Singer setSinger(ResultSet rs) throws Exception {
        Singer singer = null;
        if (rs.next()) {
            singer = setModelFromResultSet(rs);
        }
        return singer;
    }

    private List<Singer> setSingerList(ResultSet rs) throws Exception {
        List<Singer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(setModelFromResultSet(rs));
        }
        return list;
    }

    private int setCountAll(ResultSet rs) throws Exception {
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
}
