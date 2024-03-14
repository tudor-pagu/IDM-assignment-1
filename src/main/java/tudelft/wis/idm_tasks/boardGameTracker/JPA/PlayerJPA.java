package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import jakarta.persistence.*;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="player")
public class PlayerJPA implements Player {
    @Id
    public String nickname;

    public String name;

    @ManyToMany (targetEntity = tudelft.wis.idm_tasks.boardGameTracker.JPA.BoardGameJPA.class)
    private Collection<BoardGame> collection;

    public PlayerJPA() {

    }

    public PlayerJPA(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
        this.collection = new ArrayList<>();
    }
    @Override
    public String getPlayerName() {
        return this.name;
    }

    @Override
    public String getPlayerNickName() {
        return this.nickname;
    }

    @Override
    public Collection<BoardGame> getGameCollection() {
        return this.collection;
    }

    @Override
    public String toVerboseString() {
        return null;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setName(String name) {
        this.name = name;
    }
}
