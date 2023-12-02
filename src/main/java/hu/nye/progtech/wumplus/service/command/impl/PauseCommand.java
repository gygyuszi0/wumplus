package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PauseCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PauseCommand.class);

    @Override
    public boolean canProcess(String input) {
        return input.equals(CommandConst.PAUSE);
    }

    @Override
    public Optional<GameState> process(String input, Optional<GameState> gameState) {
        if (gameState.isPresent()) {
            GameState result = gameState.get().deepcCopy();
            result.setPause(true);
            return Optional.of(result);
        } else {
            logger.info("No game is running");
            return gameState;
        }
    }
}
