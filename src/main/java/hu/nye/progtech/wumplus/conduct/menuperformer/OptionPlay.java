package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.conduct.gamecontroller.Controller;
import hu.nye.progtech.wumplus.model.GameState;
import org.slf4j.Logger;

/**
 * Perform play game menu option.
 */
public class OptionPlay implements OptionPerformer {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(OptionPlay.class);

    private final Controller controller;

    public OptionPlay(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        if (gameState.isPresent()) {
            Optional<GameState> result = controller.startGame(gameState);
            return result;
        } else {
            logger.error("Game state is not present.");
            return gameState;
        }

    }
}
