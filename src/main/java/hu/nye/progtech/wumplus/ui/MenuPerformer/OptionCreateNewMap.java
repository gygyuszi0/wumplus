package hu.nye.progtech.wumplus.ui.MenuPerformer;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;

import java.util.Optional;

public class OptionCreateNewMap implements OptionPerformer {
    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        return Optional.empty();
    }
}
