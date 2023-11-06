package hu.nye.progtech.wumplus.service.map.impl;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.service.map.MapParser;

/**
 * MapParser default implementáció.
 */
public class DefaultMapParser implements MapParser {

    public static final Character WALL = 'W';
    public static final Character SPACE = '_';

    @Override
    public MapVO parseMap(List<String> rawMap) {
        MapVO result;

        List<String> map = extractMap(rawMap);
        List<List<Boolean>> fixed = extractFixed(rawMap);

        // for (int i = 0; i < map.size(); i++) {
        //      System.out.println("Row " + i);
        //      System.out.println("\tmap\t\t: " + map.get(i));
        //      System.out.println("\tfixed\t: " + fixed.get(i));
        // }

        result = new MapVO(0, 0, 0, 0, map, fixed);

        return result;
    }

    private List<String> extractMap(List<String> rawMap) {
        Integer numRows = rawMap.size();
        List<String> mapStrings = new ArrayList<>();

        for (int i = 1; i < numRows; i++) {
            mapStrings.add(rawMap.get(i));
        }

        return mapStrings;
    }

    private List<List<Boolean>> extractFixed(List<String> rawMap) {

        List<List<Boolean>> result = new ArrayList<>();

        Integer numRows = rawMap.size();
        for (int i = 1; i < numRows; i++) {

            String currentRow = rawMap.get(i);
            Integer numCols = currentRow.length();

            List<Boolean> newLine = new ArrayList<>();
            for (int j = 0; j < numCols; j++) {
                Character currentChar = currentRow.charAt(j);
                if (currentChar.equals(SPACE)) {
                    newLine.add(false);
                } else {
                    newLine.add(true);
                }
            }
            result.add(newLine);
        }
        return result;
    }
}

