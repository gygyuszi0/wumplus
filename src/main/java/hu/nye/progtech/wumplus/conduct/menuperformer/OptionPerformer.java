package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.GameState;

/**
 * Perform a menu option.
 */
public interface OptionPerformer {
    public Optional<GameState> perform(Optional<GameState> gameState);
}
