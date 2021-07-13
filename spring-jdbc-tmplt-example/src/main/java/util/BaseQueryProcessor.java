package util;

import db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseQueryProcessor<T> {

    private final ConnectionFactory connectionFactory;

    public BaseQueryProcessor(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public abstract void addModelSetPreparedStatement(PreparedStatement psm, T model) throws Exception;

    public abstract void updateModelSetPreparedStatement(PreparedStatement psm, T model) throws Exception;

    public abstract void deleteModelSetPreparedStatement(PreparedStatement psm, long id) throws Exception;

    public abstract void getModelSetPreparedStatement(PreparedStatement psm, long id) throws Exception;

    public abstract void getAllSetPreparedStatement(PreparedStatement psm) throws Exception;

    public abstract void countAllSetPreparedStatement(PreparedStatement psm) throws Exception;

    public abstract T getModelFromResultSet(ResultSet rs) throws Exception;

    public abstract int getCountFromResultSet(ResultSet rs) throws Exception;

    public boolean executeAddPreparedStatement(String sql, T model) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        addModelSetPreparedStatement(psm, model);
        psm.execute();
        psm.close();
        connectionFactory.close();
        return true;
    }

    public boolean executeUpdatePreparedStatement(String sql, T model) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        updateModelSetPreparedStatement(psm, model);
        psm.execute();
        psm.close();
        connectionFactory.close();
        return true;
    }

    public boolean executeDeletePreparedStatement(String sql, long id) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        deleteModelSetPreparedStatement(psm, id);
        psm.execute();
        psm.close();
        connectionFactory.close();
        return true;
    }

    public T executeGetPreparedStatement(String sql, long id) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        getModelSetPreparedStatement(psm, id);
        ResultSet rs = psm.executeQuery();
        T t = null;
        if (rs.next()) {
            t = getModelFromResultSet(rs);
        }
        rs.close();
        psm.close();
        connectionFactory.close();
        return t;
    }

    public List<T> executeGetAllPreparedStatement(String sql) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            list.add(getModelFromResultSet(rs));
        }
        rs.close();
        psm.close();
        connectionFactory.close();
        return list;
    }

    public int executeCountAllPreparedStatement(String sql) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = getCountFromResultSet(rs);
        }
        rs.close();
        psm.close();
        connectionFactory.close();
        return count;
    }
}
