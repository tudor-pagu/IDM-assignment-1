package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import org.junit.jupiter.api.Test;
import tudelft.wis.idm_tasks.boardGameTracker.JDBC.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerJPATest {

    @Test
    void getPlayerNickName() throws BgtException {
        JPAManager jpaManager = new JPAManager();
        PlayerJPA player = new PlayerJPA("jeffrey", "jeff");
        BoardGame game = new BoardGameJPA("wingspan", "fun");
        BoardGame game2 = new BoardGameJPA("wingspan2", "fun2");
        player.getGameCollection().add(game);
        jpaManager.persist(game);
        jpaManager.persist(player);
        jpaManager.persist(game2);

        BgtDataManager bgtDataManager = new BgtDataManagerJPA(jpaManager);

        assertEquals(bgtDataManager.findPlayersByName("jeff").stream().findFirst().get().getPlayerName(), "jeff");
    }
}