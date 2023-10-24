package hu.nye.progtech.wumplus;

import hu.nye.progtech.wumplus.model.Game;

/**
 * Wumplus Main function.
 */
public class Main {
    public static void main(String[] args) {
        Game wumplusGame = new Game();
        wumplusGame.mainLoop();
    }
}