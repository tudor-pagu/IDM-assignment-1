package tudelft.wis.idm_tasks.basicJDBC.interfaces;

import org.postgresql.util.PSQLState;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCTask2 implements JDBCTask2Interface {
    Connection connection;
    @Override
    public Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/imdb";
        String user = "postgres";
        String password = "postgres";
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            return this.connection;
        } catch(SQLException e) {
            throw new IllegalArgumentException("Connection not established");
        }
    }

    @Override
    public Collection<String> getTitlesPerYear(int year) {
        String query = "SELECT t.primary_title FROM titles t\n" +
                "WHERE t.start_year=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, year);
            statement.executeQuery();
            ResultSet s = statement.getResultSet();
            List<String> result = new ArrayList<>();
            while (s.next()) {
                result.add(s.getString("primary_title"));
            }

            return result;
        } catch(SQLException e) {
            System.out.println("Didn't work");
            return new ArrayList<>();
        }
    }

    @Override
    public Collection<String> getJobCategoriesFromTitles(String searchString) {
        searchString = "%" + searchString + "%";
        String query = "SELECT job_category FROM titles t\n" +
                "JOIN cast_info c ON c.title_id = t.title_id\n" +
                "WHERE t.primary_title LIKE ?";
        List<String> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, searchString);
            statement.execute();
            ResultSet s = statement.getResultSet();
            while (s.next()) {
                result.add(s.getString("job_category"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public double getAverageRuntimeOfGenre(String genre) {
        String query = "SELECT AVG(t.runtime) as avg_runtime FROM titles_genres g \n" +
                "JOIN titles t ON t.title_id = g.title_id\n" +
                "WHERE g.genre = ?\n";
        double result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, genre);
            statement.execute();
            ResultSet s = statement.getResultSet();
            s.next();
            result = s.getDouble("avg_runtime");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<String> getPlayedCharacters(String actorFullname) {
        List<String> result = new ArrayList<>();
        try {
            String query = "SELECT character_name FROM persons p \n" +
                    "JOIN title_person_character t ON t.person_id = p.person_id\n" +
                    "WHERE p.full_name = ?\n";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, actorFullname);
            statement.execute();
            ResultSet s = statement.getResultSet();
            while(s.next()) {
                result.add(s.getString("character_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
