package util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class BasePreparedStatement {

    private Connection conn;

    public abstract void setAddPreparedStatement(PreparedStatement psm);

    public abstract void setUpdatePreparedStatement(PreparedStatement psm);

    public abstract void setDeletePreparedStatement(PreparedStatement psm);

    public boolean executeAddPreparedStatement(String sql) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        setAddPreparedStatement(psm);
        psm.execute();
        psm.close();
        conn.close();
        return true;
    }

    public boolean executeUpdatePreparedStatement(String sql) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        setUpdatePreparedStatement(psm);
        psm.execute();
        psm.close();
        conn.close();
        return true;
    }

    public boolean executeDeletePreparedStatement(String sql) throws Exception {
        PreparedStatement psm = conn.prepareStatement(sql);
        setDeletePreparedStatement(psm);
        psm.execute();
        psm.close();
        conn.close();
        return true;
    }
}
