package tudelft.wis.idm_tasks.boardGameTracker.JDBC;


import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;

public class PlayerJDBC implements Player {
    private String name;
    private String nickname;
    private Collection<BoardGame> boardGameCollection;

    public PlayerJDBC(String name, String nickname, Collection<BoardGame> boardGameCollection) {
        this.name = name;
        this.nickname = nickname;
        this.boardGameCollection = boardGameCollection;
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
        return this.boardGameCollection;
    }

    @Override
    public String toVerboseString() {
        return "<Player name="+getPlayerName()+" nickname="+getPlayerNickName()+">";
    }
}
