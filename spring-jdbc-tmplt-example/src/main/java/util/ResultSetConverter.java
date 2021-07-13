package util;

import java.sql.ResultSet;

public interface ResultSetConverter<T> {
    T convertResultSetToModel(ResultSet rs) throws Exception;
}
