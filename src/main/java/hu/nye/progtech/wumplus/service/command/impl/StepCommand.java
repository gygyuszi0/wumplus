package hu.nye.progtech.wumplus.service.command.impl;

import com.sun.source.tree.Tree;
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

/**
 * Lépés parancs.
 */
public class StepCommand implements Command {

    private final GameState gameState;

    private final StepPerformer stepPerformer;

    public StepCommand(GameState gameState, StepPerformer stepPerformer) {
        this.gameState = gameState;
        this.stepPerformer = stepPerformer;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(CommandConst.STEP);
    }

    @Override
    public void process(String input) {
        try {
            PlayerVO stepedPlayer = stepPerformer.perform(gameState.getPlayerVO(), gameState.getMapVO());
            gameState.setPlayerVO(stepedPlayer);
        } catch (PerformerException e) {
            System.out.println("Can't perform this step");
            System.out.println(e.getMessage());
        } catch (PlayerDeadException e) {
            System.out.println("Player is dead.");
            gameState.setPlayerDead(true);
        } catch (MapQueryException e) {
            System.out.println(e.getMessage());
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}
