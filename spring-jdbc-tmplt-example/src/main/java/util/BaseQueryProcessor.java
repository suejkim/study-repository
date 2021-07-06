package util;

import db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class BaseQueryProcessor<T> {

    private final Connection conn;

    public BaseQueryProcessor(ConnectionFactory connectionFactory) {
        this.conn = connectionFactory.getConnection();
    }

    public abstract void setAddPreparedStatement(PreparedStatement psm, T model) throws Exception;

    public abstract void setUpdatePreparedStatement(PreparedStatement psm, T model) throws Exception;

    public abstract void setDeletePreparedStatement(PreparedStatement psm, long id) throws Exception;

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
}
