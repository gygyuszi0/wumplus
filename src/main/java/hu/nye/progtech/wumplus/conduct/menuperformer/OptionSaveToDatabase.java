package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import hu.nye.progtech.wumplus.service.persister.json.JsonService;
import org.slf4j.Logger;

/**
 * Perform save to database option.
 */
public class OptionSaveToDatabase implements OptionPerformer {

    private final DatabaseService databaseService;
    private final JsonService jsonService;
    private Logger logger = org.slf4j.LoggerFactory.getLogger(OptionSaveToDatabase.class);


    public OptionSaveToDatabase(DatabaseService databaseService, JsonService  jsonService) {
        this.databaseService = databaseService;
        this.jsonService = jsonService;
    }

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        try {
            if (gameState.isPresent()) {
                PlayerVO playerVO = gameState.get().getPlayerVO();
                MapVO mapVO = gameState.get().getMapVO();
                databaseService.save(playerVO, mapVO);
                saveToJson(playerVO, mapVO);
                return gameState;
            } else {
                logger.info("No game state to save");
                return gameState;
            }

        } catch (DBServiceException e) {
            logger.error("Error saving to database");
            return gameState;
        }
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    private void saveToJson(PlayerVO playerVO, MapVO mapVO) {
        PlayerWithMap playerWithMap = new PlayerWithMap(playerVO, mapVO);
        jsonService.save(playerVO.getName(), playerWithMap);
    }
}
