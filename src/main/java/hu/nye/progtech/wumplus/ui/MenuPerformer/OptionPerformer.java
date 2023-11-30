package hu.nye.progtech.wumplus.ui.MenuPerformer;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;

import java.util.Optional;

public interface OptionPerformer {
    public Optional<GameState> perform(Optional<GameState> gameState);
}
