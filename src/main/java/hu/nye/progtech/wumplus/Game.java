package hu.nye.progtech.wumplus;

/**
 * A játék fő osztálya.
 * Tartalmazza a játékos nevét
 * Bekéri a játékos nevét
 * Futtatja a main_loop-ot
 */
public class Game {

    private String playerName;

    private final String playerNamePropmt = "Mi a játékos neve? ";

    public Game() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * A játék fő függvénye.
     *
     * Bekéri a játékos nevet
     * Kezeli a menut és futtatja a funkciókat
     */
    public void mainLoop() {
        String newPlayerName = readPlayerName();
        setPlayerName(newPlayerName);
        // System.out.println(String.format("New player name : %s", getPlayerName()));

    }

    private String readPlayerName() {
        String result;
        System.out.println(playerNamePropmt);
        result = ConsolRead.read();
        return result;
    }

}
