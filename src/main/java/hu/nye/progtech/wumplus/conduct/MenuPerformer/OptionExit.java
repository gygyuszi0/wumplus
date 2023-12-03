package hu.nye.progtech.wumplus.conduct.MenuPerformer;

import hu.nye.progtech.wumplus.model.GameState;
import org.slf4j.Logger;

import java.util.Optional;


public class OptionExit implements OptionPerformer {

    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OptionExit.class);
    @Override
    public Optional<GameState> perform(Optional<GameState> gameState){
        LOGGER.info("Exit");
        return gameState;

    }
}
