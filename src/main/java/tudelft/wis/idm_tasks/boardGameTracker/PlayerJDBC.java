package tudelft.wis.idm_tasks.boardGameTracker;


import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;

public class PlayerJDBC implements Player {
    private String name;
    private String nickname;

    public PlayerJDBC(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
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
        return null;
    }

    @Override
    public String toVerboseString() {
        return "<Player name="+getPlayerName()+" nickname="+getPlayerNickName()+">";
    }
}
