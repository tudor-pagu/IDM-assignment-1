package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tudelft.wis.idm_tasks.boardGameTracker.JDBC.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BgtDataManagerJPA implements BgtDataManager {
    private Collection<Player> players;
    private Collection<BoardGame> games;

    JPAManager jpaManager;
    public BgtDataManagerJPA(JPAManager jpaManager) {
        this.jpaManager = jpaManager;
    }
    @Override
    public Player createNewPlayer(String name, String nickname) throws BgtException {
        Player player = new PlayerJPA(name, nickname);
        players.add(player);
        persistPlayer(player);
        return player;
    }

    @Override
    public Collection<Player> findPlayersByName(String name) throws BgtException {
        this.jpaManager.getEntityManager().getTransaction().begin();
        Query query = this.jpaManager.getEntityManager()
                .createQuery("SELECT p FROM PlayerJPA p WHERE p.name=:name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        BoardGameJPA game = new BoardGameJPA(name, bggURL);
        games.add(game);
        persistBoardGame(game);
        return game;
    }

    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        this.jpaManager.getEntityManager().getTransaction().begin();
        Query query = this.jpaManager.getEntityManager()
                .createQuery("SELECT g FROM BoardGameJPA g WHERE g.name=:name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws BgtException {
        PlaySession playSession = new PlaySessionJPA(date, host, game, players, winner, playtime);
        persistPlaySession(playSession);
        return playSession;
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
