package tudelft.wis.idm_tasks.boardGameTracker;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

public class BoardGameJDBC implements BoardGame {

    private String name;
    private String url;

    public BoardGameJDBC(String name, String url) {
        this.name = name;
        this.url = url;
    }

    /**
     * Returns the game name.
     *
     * @return game name
     */
    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the game's BoardGamesGeek.com URL.
     *
     * @return the URL as a string
     */
    @Override
    public String getBGG_URL() {
        return this.url;
    }

    public void setBGG_URL(String url) {
        this.url = url;
    }

    /**
     * Creates a human-readable String representation of this object.
     *
     * @return the string representation of the object
     */
    @Override
    public String toVerboseString() {
        return "Game: " + this.name + "\nBGG url: " + this.url;
    }
}
