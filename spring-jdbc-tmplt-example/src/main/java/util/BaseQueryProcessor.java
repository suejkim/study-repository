package util;

import db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseQueryProcessor<T> {

    private final Connection conn;

    public BaseQueryProcessor(ConnectionFactory connectionFactory) {
        this.conn = connectionFactory.getConnection();
    }

    public abstract void setAddPreparedStatement(PreparedStatement psm, T model) throws Exception;

    public abstract void setUpdatePreparedStatement(PreparedStatement psm, T model) throws Exception;

    public abstract void setDeletePreparedStatement(PreparedStatement psm, long id) throws Exception;

    public abstract void setGetPreparedStatement(PreparedStatement psm, long id) throws Exception;

    public abstract void setGetAllPreparedStatement(PreparedStatement psm) throws Exception;

    public abstract T setResultSet(ResultSet rs) throws Exception;

    public boolean executeAddPreparedStatement(String sql, T model) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        setAddPreparedStatement(psm, model);
        psm.execute();
        psm.close();
        conn.close();
        return true;
    }

    public boolean executeUpdatePreparedStatement(String sql, T model) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        setUpdatePreparedStatement(psm, model);
        psm.execute();
        psm.close();
        conn.close();
        return true;
    }

    public boolean executeDeletePreparedStatement(String sql, long id) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        setDeletePreparedStatement(psm, id);
        psm.execute();
        psm.close();
        conn.close();
        return true;
    }

    public T executeGetPreparedStatement(String sql, long id) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        setGetPreparedStatement(psm, id);
        ResultSet rs = psm.executeQuery();
        T t = null;
        if (rs.next()) {
            t = setResultSet(rs);
        }
        rs.close();
        psm.close();
        conn.close();
        return t;
    }

    public List<T> executeGetAllPreparedStatement(String sql) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            list.add(setResultSet(rs));
        }
        rs.close();
        psm.close();
        conn.close();
        return list;
    }
}
