package util;

import db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryExecutor {

    private final ConnectionFactory connectionFactory;

    public QueryExecutor(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public boolean execute(String sql, PreparedStatementProcessor processor) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        processor.setPreparedStatement(psm);
        psm.execute();
        psm.close();
        connectionFactory.close();
        return true;
    }

    public <T> T execute(String sql, PreparedStatementProcessor processor, ResultSetConverter<T> converter) throws Exception {
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
}
