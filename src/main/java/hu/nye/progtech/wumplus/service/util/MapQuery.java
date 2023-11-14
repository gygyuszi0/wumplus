package hu.nye.progtech.wumplus.service.util;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;

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
                    CoordinateVO newCoord = new CoordinateVO(i, j);
                    result.add(newCoord);
                }
            }
        }

        return result;
    }
}
