package tudelft.wis.idm_tasks.basicJDBC.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("hi");
        JDBCTask2Interface manager = new JDBCTask2();
        manager.getConnection();
        Collection<String> l = manager.getPlayedCharacters("Tom Cruise");
        return;
    }
}
