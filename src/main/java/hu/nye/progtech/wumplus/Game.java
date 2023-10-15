package hu.nye.progtech.wumplus;

/**
 * A játék fő osztálya.
 * Tartalmazza a játékos nevét
 * Bekéri a játékos nevét
 * Futtatja a main_loop-ot
 */
public class Game {

    private String playerName;

    public Game() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
