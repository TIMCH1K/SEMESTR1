package hikari;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    public static HikariDataSource dataSource;
    static {
        HikariConfig config = new HikariConfig("/hikari.properties");
        dataSource = new HikariDataSource(config);
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
