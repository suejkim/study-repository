package domain.dao;

import db.ConnectionFactory;
import domain.model.Singer;
import util.PreparedStatementProcessor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class SingerDaoImpl implements CommonDao<Singer> {

    private final Connection conn;

    public SingerDaoImpl(ConnectionFactory connectionFactory) {
        this.conn = connectionFactory.getConnection();
    }

    @Override
    public boolean add(Singer singer) throws Exception {
        String sql = "INSERT INTO singer(name, birth, sex, group_id) VALUES(?, ?, ?, ?)";
//        return execute(sql, new PreparedStatementProcessor() {
//            @Override
//            public void setPreparedStatement(PreparedStatement psm) throws Exception {
//                psm.setString(1, singer.getName());
//                psm.setDate(2, Date.valueOf(singer.getBirth()));
//                psm.setString(3, singer.getSex().name());
//                psm.setLong(4, singer.getGroupId());
//            }
//        });
        return execute(sql, psm -> {
            psm.setString(1, singer.getName());
            psm.setDate(2, Date.valueOf(singer.getBirth()));
            psm.setString(3, singer.getSex().name());
            psm.setLong(4, singer.getGroupId());
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
        return null;
    }

    @Override
    public List<Singer> getAll() throws Exception {
        return null;
    }

    @Override
    public int countAll() throws Exception {
        return 0;
    }

    private boolean execute(String sql, PreparedStatementProcessor processor) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        processor.setPreparedStatement(psm);
        psm.execute();
        psm.close();
        conn.close();
        return true;
    }
}
