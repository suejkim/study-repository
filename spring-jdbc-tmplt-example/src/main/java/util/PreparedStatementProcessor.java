package util;

import java.sql.PreparedStatement;

public interface PreparedStatementProcessor {
    void setPreparedStatement(PreparedStatement psm) throws Exception;
}
