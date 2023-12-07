package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import hu.nye.progtech.wumplus.service.persister.json.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Perform load database option.
 */
public class OptionLoadDatabase implements OptionPerformer {


    private static final Logger LOGGER = LoggerFactory.getLogger(OptionLoadDatabase.class);
    private final DatabaseService databaseService;

    private final JsonService jsonService;

    public OptionLoadDatabase(DatabaseService databaseService, JsonService jsonService) {
        this.databaseService = databaseService;
        this.jsonService = jsonService;
    }

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        try {
            if (!gameState.isEmpty()) {
                PlayerWithMap playerWithMap = databaseService.load(gameState.get().getPlayerName());
                Optional<PlayerWithMap> loadedFromJson = loadFromJson(gameState.get().getPlayerName());
                GameState result;
                if (!loadedFromJson.isEmpty()) {
                    result = new GameState(loadedFromJson.get().getMapVO(), loadedFromJson.get().getPlayerVO(), false, false);
                } else {
                    result = new GameState(playerWithMap.getMapVO(), playerWithMap.getPlayerVO(), false, false);
                }
                LOGGER.info("Loaded from database: " + result);
                return Optional.of(result);
            } else {
                return gameState;
            }
        } catch (DBServiceException e) {
            LOGGER.error("Error loading from database", e);
            return gameState;
        }
    }


    private Optional<PlayerWithMap> loadFromJson(String playerName) {
        return jsonService.load(playerName);
    }

}
