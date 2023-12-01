package hu.nye.progtech.wumplus.conduct.MenuPerformer;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import org.slf4j.Logger;

import java.util.Optional;

public class OptionSaveToDatabase implements OptionPerformer {

    private final DatabaseService databaseService;

    Logger logger = org.slf4j.LoggerFactory.getLogger(OptionSaveToDatabase.class);

    public OptionSaveToDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        try {
            if (gameState.isPresent()) {
                PlayerVO playerVO = gameState.get().getPlayerVO();
                MapVO mapVO = gameState.get().getMapVO();
                databaseService.save(playerVO, mapVO);
                return gameState;
            } else {
                logger.info("No game state to save");
                return gameState;
            }

        } catch (DBServiceException e) {
            logger.error("Error saving to database", e);
            return gameState;
        }
    }
}
