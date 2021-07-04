package domain.dao;


import db.ConnectionFactory;
import domain.model.SingerGroup;

import java.sql.Connection;
import java.util.List;

public class SingerGroupDaoImpl implements CommonDao<SingerGroup> {

    private final Connection conn;

    public SingerGroupDaoImpl (ConnectionFactory connectionFactory){
        this.conn = connectionFactory.getConnection();
    }

    @Override
    public boolean add(SingerGroup singerGroup) throws Exception {
        return false;
    }

    @Override
    public boolean update(SingerGroup singerGroup) throws Exception {
        return false;
    }

    @Override
    public boolean delete(long id) throws Exception {
        return false;
    }

    @Override
    public SingerGroup get(long id) throws Exception {
        return null;
    }

    @Override
    public List<SingerGroup> getAll() throws Exception {
        return null;
    }

    @Override
    public int countAll() throws Exception {
        return 0;
    }
}
