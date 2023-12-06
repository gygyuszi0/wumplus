package hu.nye.progtech.wumplus.service.persister.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.service.util.IOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public void save(String playerName, PlayerWithMap playerWithMap) {
        try {
            String serialized = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(playerWithMap);
            ioService.writeFile(serialized, saveFolder + playerName + ".json");
        } catch (JsonProcessingException e) {
            logger.error("Error while serializing player with map", e);
        }
    }
}
