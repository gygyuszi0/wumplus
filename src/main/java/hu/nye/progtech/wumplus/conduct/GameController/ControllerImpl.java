package hu.nye.progtech.wumplus.conduct.GameController;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.ui.Game.CommandPrompt;
import hu.nye.progtech.wumplus.ui.Game.MapWriter;
import hu.nye.progtech.wumplus.ui.Game.HudWriter;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

public class ControllerImpl implements Controller {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ControllerImpl.class);

    private final MapWriter mapWriter;
    private final HudWriter hudWriter;

    private final CommandPrompt commandPrompt;

    private final List<Command> commands;


    public ControllerImpl(MapWriter mapWriter, HudWriter hudWriter, CommandPrompt commandPrompt, List<Command> commands) {
        this.mapWriter = mapWriter;
        this.hudWriter = hudWriter;
        this.commandPrompt = commandPrompt;
        this.commands = commands;
    }

    @Override
    public Optional<GameState> startGame(final Optional<GameState> gameState) {
        logger.info("Game started");

        Optional<GameState> gamestateForProcess = Optional.of(gameState.get().deepcCopy());

        try {
            if (gamestateForProcess.isPresent()) {
                while (!gamestateForProcess.get().isShouldExit()) {
                    mapWriter.writeMap(gamestateForProcess);
                    hudWriter.writeHud(gamestateForProcess);

                    String command = commandPrompt.readCommand();

                    gamestateForProcess = executeCommand(command, gamestateForProcess);
                }
                if (gamestateForProcess.get().isGivUp()) {
                    logger.info("Game gave up");
                    return gameState;
                }
                if (gamestateForProcess.get().isPause()) {
                    logger.info("Game paused");
                    gamestateForProcess.get().setPause(false);
                    return gamestateForProcess;
                }
            } else {
                logger.error("Game state is not present.");
            }
        } catch (Exception e) {
            logger.error("Error in game : " + e.getMessage());
        }

        return gameState;
    }

    private Optional<GameState> executeCommand(String command, Optional<GameState> gameState) {
        for (Command c : commands) {
            if (c.canProcess(command)) {
                return c.process(command, gameState);
            }
        }
        logger.error("Unknown command : " + command);
        return gameState;
    }
}
