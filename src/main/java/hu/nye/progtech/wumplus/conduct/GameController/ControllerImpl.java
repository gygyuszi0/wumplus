package hu.nye.progtech.wumplus.conduct.GameController;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.ui.Game.CommandPrompt;
import hu.nye.progtech.wumplus.ui.Game.MapWriter;
import org.slf4j.Logger;

import java.util.Optional;

public class ControllerImpl implements Controller {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ControllerImpl.class);

    private final MapWriter mapWriter;

    private final CommandPrompt commandPrompt;

    public ControllerImpl(MapWriter mapWriter, CommandPrompt commandPrompt) {
        this.mapWriter = mapWriter;
        this.commandPrompt = commandPrompt;
    }

    @Override
    public Optional<GameState> startGame(Optional<GameState> gameState) {
        logger.info("Game started");

        if (mapExist(gameState)) {
            while (!gameState.get().isShouldExit()) {
                mapWriter.writeMap(gameState);

                String command = commandPrompt.readCommand();
            }
        } else {
            logger.error("Game state is not present.");
        }

        return gameState;
    }

    private boolean mapExist(Optional<GameState> gameState) {
        if (gameState.isPresent()) {
            char[][] result =gameState.get().getMapElement();
            return result.length > 5;
        } else {
            return false;
        }
    }
}
