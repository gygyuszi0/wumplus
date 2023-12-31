package hu.nye.progtech.wumplus.service.command.impl;

import java.util.Optional;
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

    private final TurnPerformer turnPerformer;


    public TurnCommand(TurnPerformer turnPerformer) {
           this.turnPerformer = turnPerformer;
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
    public Optional<GameState> process(String input, Optional<GameState> safeGameState)  {
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        String command = tokenizer.nextToken();
        Character direction = tokenizer.nextToken().charAt(0);

        if (safeGameState.isPresent()) {
            GameState gameState = safeGameState.get();
            PlayerVO newPlayer = turnPerformer.perform(gameState.getPlayerVO(), direction);
            gameState.setPlayerVO(newPlayer);

            return Optional.of(gameState);
        } else {
            return safeGameState;
        }
    }
}
