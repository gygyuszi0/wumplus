package hu.nye.progtech.wumplus.ui.MenuPerformer;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.service.persister.map.MapParser;
import hu.nye.progtech.wumplus.service.persister.map.impl.BufferedReaderMapReader;
import hu.nye.progtech.wumplus.service.persister.map.impl.DefaultMapValidator;
import hu.nye.progtech.wumplus.service.persister.map.impl.PlayerMapValidator;
import hu.nye.progtech.wumplus.service.persister.player.PlayerParser;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

public class OptionReadFromFile implements OptionPerformer  {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(OptionReadFromFile.class);

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        String inputFile = getClass().getClassLoader().getResource("").getPath() + "wumpuszinput.txt";
        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedReaderMapReader mapReader = new BufferedReaderMapReader(bufferedReader);
            List<String> rawMap = mapReader.readMap();

            PlayerParser playerParser = new PlayerParser(rawMap.get(0));
            MapParser mapParser = new MapParser(rawMap);
            PlayerVO  playerVO = playerParser.parsePlayer(rawMap);
            MapVO  mapVO = mapParser.parseMap(rawMap);

            DefaultMapValidator mapValidator = new DefaultMapValidator();
            PlayerMapValidator playerMapValidator = new PlayerMapValidator(playerVO);
            boolean mapValid = mapValidator.validateMap(mapVO);
            boolean playerValid = playerMapValidator.validateMap(mapVO);
            if (mapValid && playerValid) {
                Optional<GameState> result = Optional.of(new GameState(mapVO, playerVO, false, false));
                logger.info("Read from file: " + inputFile);
                logger.info("New game state: " + result.get());
                return result;
            } else {
                logger.error("Error reading from file: " + inputFile);
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Error reading from file: " + inputFile);
            return Optional.empty();
        }
    }
}
