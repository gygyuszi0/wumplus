package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.Command;

import java.util.Optional;

public class GiveUpCommand implements Command {

    @Override
    public boolean canProcess(String input) {
        return input.equals(CommandConst.GIVE_UP);
    }

    @Override
    public Optional<GameState> process(String input, Optional<GameState> gameState) {
        if (gameState.isPresent()) {
            GameState result = gameState.get();
            result.setGivUp(true);
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }
}
