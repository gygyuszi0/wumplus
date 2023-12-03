package hu.nye.progtech.wumplus.service.persister.player;

import java.util.List;
import java.util.StringTokenizer;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.exception.MapParseException;
import hu.nye.progtech.wumplus.service.exception.PlayerParserException;
import hu.nye.progtech.wumplus.service.persister.map.MapParser;
import hu.nye.progtech.wumplus.service.util.Converter;
import hu.nye.progtech.wumplus.service.util.NumberOfElement;

/**
 * Raw map konvertálása Player value object-re.
 */
public class PlayerParser {

    private final String playerName;

    public PlayerParser(String player) {
        playerName = player;
    }

    /**
     * Player VO-t készítő függvény.
     *
     * @param rawMap
     *
     * @return
     *
     * @throws PlayerParserException
     *
     * @throws MapParseException
     *
     */
    public PlayerVO parsePlayer(List<String> rawMap) throws PlayerParserException, MapParseException {
        MapVO mapInfo = new MapParser(rawMap).parseMap(rawMap);

        PlayerVO result = setReadedInformation(rawMap);
        result = setGameLogicInformation(result, mapInfo);

        return result;

    }

    private PlayerVO setReadedInformation(List<String> rawMap) throws PlayerParserException {
        String row = rawMap.get(0);
        StringTokenizer tokenizer = new StringTokenizer(row, " ");

        String dimensionBlock = tokenizer.nextToken();
        String playerColumnBlock = tokenizer.nextToken();
        String playerRowBlock = tokenizer.nextToken();
        String playerDirectionBlock = tokenizer.nextToken();

        Character playerColumnValue = playerColumnBlock.charAt(0);
        Integer playerX = Converter.letterToInteger(playerColumnValue) - 1;
        Integer playerY = Integer.parseInt(playerRowBlock) - 1;
        CoordinateVO playerCoordinate = new CoordinateVO(playerX, playerY);

        Character playerDirectionValue = playerDirectionBlock.charAt(0);
        isCorrectDirection(playerDirectionValue);

        PlayerVO result = new PlayerVO(playerName, playerDirectionValue, playerCoordinate, playerCoordinate);

        return result;
    }

    private PlayerVO setGameLogicInformation(PlayerVO playerVO, MapVO mapVO) {
        Integer numberOfWumpus = NumberOfElement.count(mapVO, Element.WUMP);
        PlayerVO result = playerVO.deepCopy();
        result.setNumberOfArrows(numberOfWumpus);
        return result;
    }

    private void isCorrectDirection(Character dir) throws PlayerParserException {
        if (! PlayerConst.CORRECT_DIRECTIONS.contains(dir)) {
            throw new PlayerParserException("Wrong direction, expected one of : " + PlayerConst.CORRECT_DIRECTIONS);
        }
    }
}
