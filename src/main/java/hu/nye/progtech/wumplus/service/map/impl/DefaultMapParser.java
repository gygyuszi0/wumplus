package hu.nye.progtech.wumplus.service.map.impl;

import hu.nye.progtech.wumplus.model.Map;
import hu.nye.progtech.wumplus.service.map.MapParser;

import java.util.ArrayList;
import java.util.List;

public class DefaultMapParser implements MapParser {

    public static final String WALL = "W";
    public static final String SPACE = "_";

    @Override
    public Map parseMap(List<String> rawMap) {
        Map result;

        List<String> map = extractMap(rawMap);
        List<List<Boolean>> fixed = extractFixed(rawMap);

        System.out.println("map\t: " + map);
        System.out.println("fixed\t: " + fixed);

        result = new Map(0,0,0,0,map, fixed);

        return result;
    }

    private List<String> extractMap(List<String> rawMap){
        Integer numRows = rawMap.size();
        List<String> mapStrings = new ArrayList<>();

        for (int i = 1; i < numRows; i++) {
            mapStrings.add(rawMap.get(i));
        }

        return mapStrings;
    }

    private List<List<Boolean>> extractFixed(List<String> rawMap){

        List<List<Boolean>> result = new ArrayList<>();

        Integer numRows = rawMap.size();
        for (int i = 1; i < numRows; i++) {

            String currentRow = rawMap.get(i);
            Integer numCols = currentRow.length();

            List<Boolean> new_line = new ArrayList<>();
            for (int j = 0; j < numCols; j++) {
                Character currentChar = currentRow.charAt(j);
                if (currentChar.equals(SPACE)){
                    new_line.add(false);
                }
                else{
                    new_line.add(true);
                }
            }
            result.add(new_line);
        }
        return result;
    }
}

