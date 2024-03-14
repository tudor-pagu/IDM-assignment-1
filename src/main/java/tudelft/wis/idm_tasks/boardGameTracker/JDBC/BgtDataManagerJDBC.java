package tudelft.wis.idm_tasks.boardGameTracker.JDBC;

import tudelft.wis.idm_tasks.boardGameTracker.Credentials;
import tudelft.wis.idm_tasks.boardGameTracker.PlaySessionJDBC_NoCon;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class BgtDataManagerJDBC implements BgtDataManager {
    Connection connection;
    private List<PlaySessionJDBC_NoCon> sessions = new LinkedList<>();


    public BgtDataManagerJDBC() {
        getConnection();
    }

    public Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/idm1pg";
        String user = Credentials.user;
        String password = Credentials.password;
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
        try {
            String sql = "SELECT p.name, p.nickname FROM player p " +
                    "WHERE p.name LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            name = "%" + name + "%";
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Collection<Player> playerCollection = new ArrayList<>();
            while (rs.next()) {
                String pname = rs.getString(1);
                String pnickname = rs.getString(2);
                Collection<BoardGame> bgCollection = getPlayerCollection(pnickname);
                playerCollection.add(new PlayerJDBC(pname, pnickname, bgCollection));
            }
            return playerCollection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        BoardGame bg = new BoardGameJDBC(name, bggURL);
        try {
            String sql = "INSERT INTO board_game (name, url) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, bggURL);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bg;
    }

    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        try {
            String sql = "SELECT bg.name, bg.url FROM board_game bg " +
                    "WHERE bg.name LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
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
        PlaySessionJDBC_NoCon session = new PlaySessionJDBC_NoCon(date, host, game, playtime, players, winner);
        sessions.add(session);
        return session;
    }

    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        Collection<PlaySession> result = new LinkedList<PlaySession>();
        for (PlaySessionJDBC_NoCon session : sessions) {
            if (session.getDate().equals(date)) {
                result.add(session);
            }
        }
        return result;
    }

    @Override
    public void persistPlayer(Player player) {
        try {
            String dquery = "DELETE FROM player_boardgame pb " +
                    "WHERE pb.player_nickname = ?";
            PreparedStatement deletePs = connection.prepareStatement(dquery);
            deletePs.setString(1, player.getPlayerNickName());
            deletePs.execute();

            String query = "INSERT INTO player (name, nickname) VALUES (?, ?) " +
                    "ON CONFLICT (nickname) DO UPDATE " +
                    "SET name = excluded.name";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player.getPlayerName());
            statement.setString(2, player.getPlayerNickName());
            statement.execute();

            for (BoardGame bg : player.getGameCollection()) {
                persistBoardGame(bg);
                String bgpQuery = "INSERT INTO player_boardgame (player_nickname, boardgame_name) " +
                        "VALUES (?, ?)";
                PreparedStatement ps = connection.prepareStatement(bgpQuery);
                ps.setString(1, player.getPlayerNickName());
                ps.setString(2, bg.getName());
                ps.execute();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persistPlaySession(PlaySession session) {

    }

    @Override
    public void persistBoardGame(BoardGame game) {
        String name = game.getName();
        String bggURL = game.getBGG_URL();
        try {
            String sql = "INSERT INTO board_game (name, url) VALUES (?, ?) " +
                    "ON CONFLICT (name) DO UPDATE " +
                    "SET url = excluded.url";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, bggURL);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
