package hu.nye.progtech.wumplus.conduct.GameController;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.ui.Game.CommandPrompt;
import hu.nye.progtech.wumplus.ui.Game.MapWriter;
import hu.nye.progtech.wumplus.ui.Game.HudWriter;
import org.slf4j.Logger;

import java.util.Optional;

public class ControllerImpl implements Controller {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ControllerImpl.class);

    private final MapWriter mapWriter;
    private final HudWriter hudWriter;

    private final CommandPrompt commandPrompt;

    public ControllerImpl(MapWriter mapWriter, HudWriter hudWriter, CommandPrompt commandPrompt) {
        this.mapWriter = mapWriter;
        this.hudWriter = hudWriter;
        this.commandPrompt = commandPrompt;
    }

    @Override
    public Optional<GameState> startGame(Optional<GameState> gameState) {
        logger.info("Game started");

        try {
            if (gameState.isPresent()) {
                while (!gameState.get().isShouldExit()) {
                    mapWriter.writeMap(gameState);
                    hudWriter.writeHud(gameState);

                    String command = commandPrompt.readCommand();
                }
            } else {
                logger.error("Game state is not present.");
            }
        } catch (Exception e) {
            logger.error("Error in game : " + e.getMessage());
        }

        return gameState;
    }
}
