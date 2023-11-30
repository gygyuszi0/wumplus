package hu.nye.progtech.wumplus.ui.MenuPerformer;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class OptionLoadDatabase implements OptionPerformer {


    private static final Logger LOGGER = LoggerFactory.getLogger(OptionLoadDatabase.class);
    private final DatabaseService databaseService;

    public OptionLoadDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        try {
            if (!gameState.isEmpty()) {
                PlayerWithMap playerWithMap = databaseService.load(gameState.get().getPlayerName());
                GameState result = new GameState(playerWithMap.getMapVO(), playerWithMap.getPlayerVO(), false, false);
                LOGGER.info("Loaded from database: " + result);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (DBServiceException e) {
            LOGGER.error("Error loading from database", e);
            return Optional.empty();
        }
    }
}
