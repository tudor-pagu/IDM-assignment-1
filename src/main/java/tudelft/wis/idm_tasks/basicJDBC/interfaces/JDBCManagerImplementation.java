package tudelft.wis.idm_tasks.basicJDBC.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class JDBCManagerImplementation implements JDBCManager {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/imdb";
        String user = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url, user, password);
    }
}
