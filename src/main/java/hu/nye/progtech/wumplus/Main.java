package hu.nye.progtech.wumplus;

import hu.nye.progtech.wumplus.model.Game;

import java.io.Console;


/**
 * Wumplus Main function.
 */
public class Main {
    public static void main(String[] args) {
        console.writer().println("Consol object print");
        Game wumplusGame = new Game();
        wumplusGame.mainLoop();
    }
}