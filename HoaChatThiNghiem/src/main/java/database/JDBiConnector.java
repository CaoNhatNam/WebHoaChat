package database;

import org.jdbi.v3.core.Jdbi;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.SQLException;

public class JDBiConnector {

    static Jdbi jdbi;

    public static Jdbi me() {
        if (jdbi == null) makeConnect();
        return jdbi;
    }

    private static void makeConnect() {
        try {
            MysqlDataSource source = new MysqlDataSource();
            source.setURL("jdbc:mysql://" + DbProperties.host() + ":" + DbProperties.port() + "/" + DbProperties.dbName());
            source.setUser(DbProperties.user());
            source.setPassword(DbProperties.pass());
            source.setAutoReconnect(true);
            source.setUseCompression(true);
            jdbi= Jdbi.create(source);
        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
