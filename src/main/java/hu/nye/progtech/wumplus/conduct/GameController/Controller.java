package hu.nye.progtech.wumplus.conduct.GameController;

import hu.nye.progtech.wumplus.model.GameState;

import java.util.Optional;

public interface Controller {
    public Optional<GameState> startGame(Optional<GameState> gameState);
}
