package hu.nye.progtech.wumplus.service.command.impl;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.service.command.performer.TurnPerformer;

/**
 * Fordulás parancs implementációja.
 */
public class TurnCommand implements Command {

    private final GameState gameState;



    public TurnCommand(GameState gameState) {
        this.gameState = gameState;
    }

    private static final String TURN_PATTERN = String.format("^%s [%c,%c]",
            CommandConst.TURN,
            CommandConst.TURN_RIGHT,
            CommandConst.TURN_LEFT);

    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(TURN_PATTERN, input);
    }

    @Override
    public void process(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        String command = tokenizer.nextToken();
        Character direction = tokenizer.nextToken().charAt(0);

        PlayerVO newPlayer = TurnPerformer.perform(gameState.getPlayerVO(), direction);
        gameState.setPlayerVO(newPlayer);
    }

    @Override
    public String toString() {
        return "TurnCommand{" +
                "gameState=" + gameState +
                '}';
    }

    public GameState getGameState() {
        return gameState;
    }
}
