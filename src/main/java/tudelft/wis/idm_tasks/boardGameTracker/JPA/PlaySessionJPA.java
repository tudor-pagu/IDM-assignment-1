package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import jakarta.persistence.*;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "playsession")
public class PlaySessionJPA implements PlaySession {

    @Id
    @GeneratedValue
    private int id;
    private Date date;
    @ManyToOne(targetEntity = PlayerJPA.class, fetch = FetchType.EAGER)
    private Player host;
    @ManyToOne(targetEntity = BoardGameJPA.class, fetch = FetchType.EAGER)
    private BoardGame game;
    @ManyToMany(targetEntity = PlayerJPA.class, fetch = FetchType.EAGER)
    private Collection<Player> players;
    @ManyToOne(targetEntity = PlayerJPA.class, fetch = FetchType.EAGER)
    private Player winner;
    private int playtime;

    public PlaySessionJPA(Date date, Player host, BoardGame game, Collection<Player> players, Player winner, int playTime) {
        this.date = date;
        this.host = host;
        this.game = game;
        this.players = players;
        this.winner = winner;
        this.playtime = playTime;
    }

    public PlaySessionJPA() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Player getHost() {
        return host;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public BoardGame getGame() {
        return game;
    }

    public void setGame(BoardGame game) {
        this.game = game;
    }

    public Collection<Player> getAllPlayers() {
        return players;
    }

    public void setAllPlayers(Collection<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playTime) {
        this.playtime = playTime;
    }

    public String toVerboseString() {
        return null;
    }
}
