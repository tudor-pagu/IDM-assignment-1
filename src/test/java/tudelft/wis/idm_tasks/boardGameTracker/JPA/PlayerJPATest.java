package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import org.junit.jupiter.api.Test;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerJPATest {

    @Test
    void getPlayerNickName() {
        JPAManager jpaManager = new JPAManager();
        PlayerJPA player = new PlayerJPA("jeffrey", "third");
        BoardGame game = new BoardGameJPA("wingspan", "fun");
        BoardGame game2 = new BoardGameJPA("wingspan2", "fun2");
        player.getGameCollection().add(game);
        jpaManager.persist(game);
        jpaManager.persist(player);
        jpaManager.persist(game2);
    }
}