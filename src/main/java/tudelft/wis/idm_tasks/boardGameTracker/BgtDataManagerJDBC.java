package tudelft.wis.idm_tasks.boardGameTracker;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

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

    @Override
    public Player createNewPlayer(String name, String nickname) throws BgtException {
        return null;
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
