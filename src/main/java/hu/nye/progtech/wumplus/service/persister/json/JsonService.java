package hu.nye.progtech.wumplus.service.persister.json;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.service.util.IOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON műveletek.
 */
public class JsonService {

    private final Logger logger = LoggerFactory.getLogger(JsonService.class);
    private final ObjectMapper objectMapper;
    private final IOService ioService;
    private final String saveFolder;

    public JsonService(ObjectMapper objectMapper, IOService ioService) {
        this.objectMapper = objectMapper;
        this.ioService = ioService;
        this.saveFolder = getClass().getClassLoader().getResource("").getPath().replace('\\', '/');
    }

    /**
     * Játékos és map mentése.
     *
     * @param playerName játékos neve
     * @param playerWithMap játékos és map objektum
     */
    public void save(String playerName, PlayerWithMap playerWithMap) {
        try {
            String serialized = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(playerWithMap);
            ioService.writeFile(serialized, saveFolder + playerName + ".json");
        } catch (JsonProcessingException e) {
            logger.error("Error while serializing player with map", e);
        }
    }

    /**
     * Játékos és map fileból olvasása.
     *
     * @param playerName Játékos neve
     *
     * @return Játékos és map ha van.
     */
    public Optional<PlayerWithMap> load(String playerName) {
        String serialized = ioService.readFile(saveFolder + playerName + ".json");
        try {
            if (serialized != null) {
                PlayerWithMap playerWithMap = objectMapper.readValue(serialized, PlayerWithMap.class);
                return Optional.of(playerWithMap);
            } else {
                return Optional.empty();
            }
        } catch (JsonProcessingException e) {
            logger.error("Error while deserializing player with map", e);
            return Optional.empty();
        } catch (IOException e) {
            logger.error("Error while reading file", e);
            return Optional.empty();
        }
    }
}