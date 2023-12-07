package hu.nye.progtech.wumplus.service.command.impl;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.service.command.performer.StepPerformer;
import hu.nye.progtech.wumplus.service.exception.MapParseException;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.exception.PlayerDeadException;
import hu.nye.progtech.wumplus.service.util.MapQuery;
import org.slf4j.Logger;


/**
 * Lépés parancs.
 */
public class StepCommand implements Command {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(StepCommand.class);
    private final StepPerformer stepPerformer;

    public StepCommand(StepPerformer stepPerformer) {
        this.stepPerformer = stepPerformer;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(CommandConst.STEP);
    }

    @Override
    public Optional<GameState> process(String input, Optional<GameState> safeGameState) {
        if  (safeGameState.isPresent()) {
            try {
                GameState gameState = safeGameState.get();
                PlayerVO stepedPlayer = stepPerformer.perform(gameState.getPlayerVO(), gameState.getMapVO());
                gameState.setPlayerVO(stepedPlayer);

                if (stepedPlayer.getCoordinate().equals(stepedPlayer.getStartCoordinate())){
                    gameState.setShouldExit(true);
                    gameState.setPlayerWon(true);
                }

                return Optional.of(gameState);
            } catch (PerformerException e) {
                logger.error("Can't perform this step" + e.getMessage());
                return safeGameState;
            } catch (PlayerDeadException e) {
                logger.error("Player is dead");
                GameState gameState = safeGameState.get();
                gameState.setPlayerDead(true);
                gameState.setShouldExit(true);
                return Optional.of(gameState);
            } catch (MapQueryException e) {
                logger.error("Error in map query" + e.getMessage());
                return safeGameState;
            }
        } else {
            logger.error("No game state");
            return Optional.empty();
        }
    }
}
