package hu.nye.progtech.wumplus.service.persister.map;

import static hu.nye.progtech.wumplus.model.constants.Element.STATIC_ELEMENT;
import static hu.nye.progtech.wumplus.model.constants.Element.VALID_ROW_REGEX;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.service.exception.MapParseException;


/**
 * Listát (raw map) alakít value objectté.
 *
 * A Row map tartalmazza az első sort is
 */
public class MapParser {

    private int numberOfRows;
    private int numberOfColumns;

    public MapParser(List<String> rawMap) {
        String header = rawMap.get(0);
        StringTokenizer tokenizer = new StringTokenizer(header, " ");
        String dimensionBlock =  tokenizer.nextToken();
        Integer dimensions = Integer.parseInt(dimensionBlock);

        this.numberOfRows = dimensions;
        this.numberOfColumns = dimensions;
    }

    /**
     * String lista (raw nap) konvertálása value objecté.
     *
     * @param rawMap
     *
     * @return
     *
     * @throws MapParseException
     *
     */
    public MapVO parseMap(List<String> rawMap) throws MapParseException {
        checkNumberOfRows(rawMap);
        checkNumberOfColumns(rawMap);
        checkForInvalidValues(rawMap);

        char[][] map = getMap(rawMap);
        boolean[][] fixed = getFixed(map);

        return new MapVO(numberOfRows, numberOfColumns, map, fixed);
    }

    private void checkNumberOfRows(List<String> rows) throws MapParseException {
        if (rows.size() - 1 != numberOfRows) {
            throw new MapParseException("Number of rows must be " + numberOfRows);
        }
    }

    private void checkNumberOfColumns(List<String> rows) throws MapParseException {
        for (int i = 1; i < rows.size(); i++) {
            String row = rows.get(i);
            if (row.length() != numberOfColumns) {
                throw new MapParseException("Number of columns must be " + numberOfColumns);
            }
        }
    }

    private void checkForInvalidValues(List<String> rows) throws MapParseException {
        for (int i = 1; i < rows.size(); i++) {
            String row = rows.get(i);
            if (!Pattern.matches(VALID_ROW_REGEX, row)) {
                throw new MapParseException("Row contains invalid characters");
            }
        }
    }

    private char[][] getMap(List<String> rawMap) {
        char[][] result = new char[numberOfRows][];

        for (int i = 0; i < numberOfRows; i++) {
            result[i] = new char[numberOfColumns];

            String line = rawMap.get(i + 1);
            String[] parts = line.split("");

            for (int j = 0; j < numberOfColumns; j++) {
                char parsed = parts[j].charAt(0);
                result[i][j] = parsed;
            }
        }

        return result;
    }

    private boolean[][] getFixed(char[][] map) {
        boolean[][] result = new boolean[numberOfRows][];

        for (int i = 0; i < numberOfRows; i++) {
            result[i] = new boolean[numberOfColumns];

            for (int j = 0; j < numberOfColumns; j++) {
                Character currentElement = map[i][j];
                result[i][j] = STATIC_ELEMENT.contains(currentElement);
            }
        }

        return result;
    }

}