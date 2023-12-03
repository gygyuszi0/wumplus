package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.GameState;
import org.slf4j.Logger;

/**
 * Perform create new map option.
 */
public class OptionCreateNewMap implements OptionPerformer {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(OptionCreateNewMap.class);

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        logger.info("Currently not avialable");
        return gameState;
    }
}
