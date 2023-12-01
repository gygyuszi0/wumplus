package hu.nye.progtech.wumplus.conduct.MenuPerformer;

import hu.nye.progtech.wumplus.model.GameState;
import org.slf4j.Logger;

import java.util.Optional;

public class OptionCreateNewMap implements OptionPerformer {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(OptionCreateNewMap.class);

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        logger.info("Currently not avialable");
        return gameState;
    }
}
