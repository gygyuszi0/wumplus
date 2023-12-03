package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.GameState;
import org.slf4j.Logger;

/**
 * Perfrom exit option.
 */
public class OptionExit implements OptionPerformer {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(OptionExit.class);

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        logger.info("Exit");
        return gameState;

    }
}
