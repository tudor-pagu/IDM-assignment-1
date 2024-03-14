package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import jakarta.persistence.EntityManager;
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
        //return players.stream().filter(x -> x.getPlayerName().equals(name)).toList();
       // CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
       // CriteriaQuery<T>
        return null;
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
        return games.stream().filter(x -> x.getName().equals(name)).toList();
    }

    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws BgtException {
        return null;
    }

    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        EntityManager em = jpaManager.getEntityManager();
        
    }

    @Override
    public void persistPlayer(Player player) {
        jpaManager.persist(player);
    }

    @Override
    public void persistPlaySession(PlaySession session) {
        jpaManager.persist(session);
    }

    @Override
    public void persistBoardGame(BoardGame game) {
        jpaManager.persist(game);
    }
}
