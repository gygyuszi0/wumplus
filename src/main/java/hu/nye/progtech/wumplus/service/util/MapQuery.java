package hu.nye.progtech.wumplus.service.util;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;

/**
 * Mapről olvas információkat.
 *
 * MapVO tömbjei 0-tól indexelnek
 * PlayerVO koordinátái 1-től.
 */
public class MapQuery {
    /**
     * Megkeresi az összes előforduló elem koordinátáját.
     *
     * @param element Melyik elemet keresi?
     *
     * @param mapVO Melyik map-en?
     *
     * @return Az elemek koordinátáinak listája 0-tól indexelve.
     *
     */
    public static List<CoordinateVO> allCoordinateOf(Character element, MapVO mapVO) {
        List<CoordinateVO> result = new ArrayList<>();
        Integer rows = mapVO.getNumberOfRows();
        Integer cols = mapVO.getNumberOfColumns();
        char [][] map = mapVO.getMap();

        for (int i = 0; i < rows; i++) {
            char[] currentRow = map[i];
            for (int j = 0; j < cols; j++) {
                Character currentElement = currentRow[j];
                if (currentElement.equals(element)) {
                    CoordinateVO newCoord = new CoordinateVO(j, i);
                    result.add(newCoord);
                }
            }
        }

        return result;
    }

    /**
     * Visszatér a koordináta által meghatározott mezővel.
     *
     * @param coordinateVO Melyik kordináta?
     *
     * @param mapVO Melyik map-en keres?
     *
     * @return Melyik elem található itt
     *
     * @throws MapQueryException ha nincs ilyen koordináta a map-en.
     *
     */
    public static Character getFieldByCoordinate(CoordinateVO coordinateVO, MapVO mapVO) throws MapQueryException {
        Character result;

        char[][] map = mapVO.getMap();
        Integer fieldX = coordinateVO.getCoordX();
        Integer fieldY = coordinateVO.getCoordY();

        try {
            result = map[fieldY][fieldX];
        } catch (Exception e) {
            throw new MapQueryException("This coordinate out of the map : " + coordinateVO);
        }

        return result;
    }

    /**
     * Melyik koordináta van a játékos előtt.
     *
     * @param playerVO Melyik játékos?
     *
     * @param mapVO Melyik map-en?
     *
     * @return
     *
     */
    public static CoordinateVO getCoordFrontOfThePlayer(PlayerVO playerVO, MapVO mapVO) {
        CoordinateVO result = playerVO.getCoordinate();
        Character direction = playerVO.getDirection();
        Integer step = 0;

        if (direction == PlayerConst.NORTH || direction == PlayerConst.SOUTH) {
            step = (direction == PlayerConst.NORTH) ? -1 : 1;
            result.setCoordY(result.getCoordY() + step);
        }
        if (direction == PlayerConst.EAST || direction == PlayerConst.WEST) {
            step = (direction == PlayerConst.WEST) ? -1 : 1;
            result.setCoordX(result.getCoordX() + step);
        }

        return result;
    }

}
