package domain.dao;


import db.ConnectionFactory;
import domain.model.SingerGroup;
import util.BaseQueryProcessor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class SingerGroupDaoImpl extends BaseQueryProcessor<SingerGroup> implements CommonDao<SingerGroup> {

    public SingerGroupDaoImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public boolean add(SingerGroup singerGroup) throws Exception {
        String sql = "INSERT INTO singer_group(name, debut_date, agency) VALUES(?, ?, ?)";
        return executeAddPreparedStatement(sql, singerGroup);
    }

    @Override
    public boolean update(SingerGroup singerGroup) throws Exception {
        String sql = "update singer_group set name = ?, debut_date = ?, agency = ? where id = ?";
        return executeUpdatePreparedStatement(sql, singerGroup);
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
    public void setAddPreparedStatement(PreparedStatement psm, SingerGroup model) throws Exception {
        psm.setString(1, model.getName());
        psm.setDate(2, Date.valueOf(model.getDebutDate()));
        psm.setString(3, model.getAgency());
    }

    @Override
    public void setUpdatePreparedStatement(PreparedStatement psm, SingerGroup model) throws Exception {
        psm.setString(1, model.getName());
        psm.setDate(2, Date.valueOf(model.getDebutDate()));
        psm.setString(3, model.getAgency());
        psm.setLong(4, model.getId());
    }

    @Override
    public void setDeletePreparedStatement(PreparedStatement psm, long id) throws Exception {

    }
}
