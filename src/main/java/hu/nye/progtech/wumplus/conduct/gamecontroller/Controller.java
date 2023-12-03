package hu.nye.progtech.wumplus.conduct.gamecontroller;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.GameState;

/**
 * Control the menu choices.
 */
public interface Controller {
    public Optional<GameState> startGame(Optional<GameState> gameState);
}
