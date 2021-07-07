package domain.dao;


import db.ConnectionFactory;
import domain.model.SingerGroup;
import util.BaseQueryProcessor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String sql = "delete from singer_group where id = ?";
        return executeDeletePreparedStatement(sql, id);
    }

    @Override
    public SingerGroup get(long id) throws Exception {
        String sql = "select * from singer_group where id = ?";
        return executeGetPreparedStatement(sql, id);
    }

    @Override
    public List<SingerGroup> getAll() throws Exception {
        String sql = "select * from singer_group";
        return executeGetAllPreparedStatement(sql);
    }

    @Override
    public int countAll() throws Exception {
        String sql = "select count(*) from singer_group";
        return executeCountAllPreparedStatement(sql);
    }

    @Override
    public void addModelSetPreparedStatement(PreparedStatement psm, SingerGroup model) throws Exception {
        psm.setString(1, model.getName());
        psm.setDate(2, Date.valueOf(model.getDebutDate()));
        psm.setString(3, model.getAgency());
    }

    @Override
    public void updateModelSetPreparedStatement(PreparedStatement psm, SingerGroup model) throws Exception {
        psm.setString(1, model.getName());
        psm.setDate(2, Date.valueOf(model.getDebutDate()));
        psm.setString(3, model.getAgency());
        psm.setLong(4, model.getId());
    }

    @Override
    public void deleteModelSetPreparedStatement(PreparedStatement psm, long id) throws Exception {
        psm.setLong(1, id);
    }

    @Override
    public void getModelSetPreparedStatement(PreparedStatement psm, long id) throws Exception {
        psm.setLong(1, id);
    }

    @Override
    public void getAllSetPreparedStatement(PreparedStatement psm) throws Exception {

    }

    @Override
    public void countAllSetPreparedStatement(PreparedStatement psm) throws Exception {

    }

    @Override
    public SingerGroup getModelFromResultSet(ResultSet rs) throws Exception {
        SingerGroup singerGroup = new SingerGroup();
        singerGroup.setId(rs.getLong("id"));
        singerGroup.setName(rs.getString("name"));
        singerGroup.setDebutDate(rs.getDate("debut_date").toLocalDate());
        singerGroup.setAgency(rs.getString("agency"));
        return singerGroup;
    }

    @Override
    public int getCountFromResultSet(ResultSet rs) throws Exception {
        return rs.getInt(1);
    }
}
