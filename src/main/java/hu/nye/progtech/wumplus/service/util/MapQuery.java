package hu.nye.progtech.wumplus.service.util;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.Element;
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

    /**
     * Beállítja az adott koordináta elemet.
     *
     * @param mapVO Melyik mapen
     * @param coordinateVO Melyik koordináta
     * @param element Milyen elem legyen

     * @return A beállított mapVO-t adja vissza.
     *
     * @throws MapQueryException ha nincs ilyen koordináta a map-en.
     */
    public static MapVO setElementByCoordinate(MapVO mapVO, CoordinateVO coordinateVO, Character element) throws MapQueryException {
        char[][] resultMap = mapVO.getMap();
        boolean[][] resultFixed = mapVO.getFixed();
        Integer dimension = mapVO.getNumberOfRows();
        boolean newFixed = element.equals(Element.WALL);

        try {
            resultMap[coordinateVO.getCoordY()][coordinateVO.getCoordX()] = element;
            resultFixed[coordinateVO.getCoordY()][coordinateVO.getCoordX()] = newFixed;
            return new MapVO(dimension, dimension, resultMap, resultFixed);
        } catch (Exception e) {
            throw new MapQueryException("This coordinate out of the map : " + coordinateVO);
        }
    }

    /**
     * Visszaadja a megadott sorban lévő elemeket.
     *
     * @param mapVO Melyik mapen
     * @param row Melyik sor?
     *
     * @return A sorban lévő elemeket.
     *
     * @throws MapQueryException ha nincs ilyen sor a map-en.
     */
    public static char[] getRow(MapVO mapVO, Integer row) throws MapQueryException {
        char[][] map = mapVO.getMap();

        try {
            return map[row];
        } catch (Exception e) {
            throw new MapQueryException("This coordinate out of the map : " + row);
        }

    }

    /**
     * Visszaadja az adott indextől egy irányba elhelyezkedő első elemet.
     *
     * @param array Melyik tömb elemi
     * @param index Melyik indextől
     * @param element Melyik elem?
     * @param direction Melyik irányba?
     *
     * @return A megadott indextől egy irányba elhelyezkedő első elem.
     *
     * @throws MapQueryException ha nincs ilyen index a tömbben.
     */
    public static Character elementFromIndex(char[] array, Integer index, Element element, boolean direction) throws MapQueryException {
        Integer directionSign = direction ? 1 : -1;
        Integer maxElement = array.length;
        Character result;

        try {
            result = array[index];
        } catch (Exception e) {
            throw new MapQueryException("The given index is out of the array");
        }

        int i = index;
        while (i < maxElement && i > 0) {
            result = array[i];
            i = i + directionSign;
        }
        if (result.equals(element)) {
            return result;
        } else {
            throw new MapQueryException("The given element is not found");
        }
    }

    public static String serializeMap(MapVO mapVO) {
        StringBuilder result = new StringBuilder();
        char[][] map = mapVO.getMap();
        for (char[] row : map) {
            for (char element : row) {
                result.append(element);
            }
            result.append('\n');
        }
        return result.toString();
    }

    public static MapVO deserializeMap(String map) {
        String[] rows = map.split("\n");
        char[][] resultMap = new char[rows.length][rows[0].length()];
        boolean[][] resultFixed = new boolean[rows.length][rows[0].length()];

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            for (int j = 0; j < row.length(); j++) {
                resultMap[i][j] = row.charAt(j);
                if (row.charAt(j) == Element.WALL || row.charAt(j) == Element.PIT) {
                    resultFixed[i][j] = true;
                } else {
                    resultFixed[i][j] = false;
                }
            }
        }

        return new MapVO(resultMap.length, resultMap[0].length, resultMap, resultFixed);
    }
}
