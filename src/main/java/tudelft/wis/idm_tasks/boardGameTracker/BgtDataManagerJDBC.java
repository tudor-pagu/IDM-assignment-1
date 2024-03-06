package tudelft.wis.idm_tasks.boardGameTracker;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.*;
import java.util.ArrayList;
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
        Connection con = this.getConnection();
        try {
            String sql = "SELECT p.name, p.nickname FROM player p " +
                    "WHERE p.name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            name = "%" + name + "%";
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Collection<Player> playerCollection = new ArrayList<>();
            while (rs.next()) {
                String pname = rs.getString(1);
                String pnickname = rs.getString(2);
                Collection<BoardGame> bgCollection = new ArrayList<>();
                String bgCollSql = "SELECT bg.name, bg.url FROM board_game bg " +
                        "JOIN player_boardgame pb ON pb. " +
                        "WHERE pb.player_nickname = " + pname;
                Statement statement = con.createStatement();
                ResultSet collectionRs = statement.executeQuery(bgCollSql);
                bgCollection.add(new PlayerJDBC(pname, pnickname, ));
            }
            return playerCollection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        return null;
    }

    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        Connection con = this.getConnection();
        try {
            String sql = "SELECT bg.name, bg.url FROM board_game bg " +
                    "WHERE bg.name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            name = "%" + name + "%";
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Collection<BoardGame> bgCollection = new ArrayList<>();
            while (rs.next()) {
                String bgname = rs.getString(1);
                String bgurl = rs.getString(2);
                bgCollection.add(new BoardGameJDBC(bgname, bgurl));
            }
            return bgCollection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
