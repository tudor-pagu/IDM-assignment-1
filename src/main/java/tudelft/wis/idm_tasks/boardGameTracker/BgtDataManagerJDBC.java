package tudelft.wis.idm_tasks.boardGameTracker;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BgtDataManagerJDBC implements BgtDataManager {
    Connection connection;
    public Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/idm1pg";
        String user = "postgres";
        String password = "postgres";
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            return this.connection;
        } catch(SQLException e) {
            throw new IllegalArgumentException("Connection not established");
        }
    }

    public Collection<BoardGame> getPlayerCollection(String nickname)  throws BgtException{
        String query = "\n" +
                "SELECT b.name, b.url FROM player_boardgame pb \n" +
                "JOIN board_game b ON b.name = pb.boardgame_name\n" +
                "WHERE pb.player_nickname=?";
        List<BoardGame> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                result.add(new BoardGameJDBC(resultSet.getString("name"),
                        resultSet.getString("url")));
            }
        } catch (SQLException e) {
            throw new BgtException();
        }
        return result;
    }
    @Override
    public Player createNewPlayer(String name, String nickname) throws BgtException {
        Player player = new PlayerJDBC(
                name,
                nickname,
                getPlayerCollection(nickname)
        );

        try {
            String query = "INSERT INTO player (name, nickname) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, nickname);
            statement.execute();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return player;
    }

    @Override
    public Collection<Player> findPlayersByName(String name) throws BgtException {
        return null;
    }

    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        return null;
    }

    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        return null;
    }

    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws BgtException {
        return null;
    }

    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        return null;
    }

    @Override
    public void persistPlayer(Player player) {

    }

    @Override
    public void persistPlaySession(PlaySession session) {

    }

    @Override
    public void persistBoardGame(BoardGame game) {

    }
}
