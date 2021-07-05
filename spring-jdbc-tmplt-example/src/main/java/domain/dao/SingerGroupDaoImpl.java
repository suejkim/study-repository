package domain.dao;


import db.ConnectionFactory;
import domain.model.SingerGroup;
import util.BasePreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SingerGroupDaoImpl extends BasePreparedStatement implements CommonDao<SingerGroup> {

    private final Connection conn;

    public SingerGroupDaoImpl(ConnectionFactory connectionFactory) {
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

    @Override
    public void setAddPreparedStatement(PreparedStatement psm) {

    }

    @Override
    public void setUpdatePreparedStatement(PreparedStatement psm) {

    }

    @Override
    public void setDeletePreparedStatement(PreparedStatement psm) {

    }
}
