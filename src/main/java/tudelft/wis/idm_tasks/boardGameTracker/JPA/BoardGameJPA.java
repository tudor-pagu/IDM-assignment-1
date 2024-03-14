package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

@Entity
@Table(name = "board_game")
public class BoardGameJPA implements BoardGame {
    @Id
    private String name;
    private String url;
    public BoardGameJPA() {

    }
    public BoardGameJPA(String name, String url) {
        this.name = name;
        this.url = url;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getBGG_URL() {
        return this.url;
    }

    @Override
    public String toVerboseString() {
        return null;
    }
}
