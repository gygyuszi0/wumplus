package hu.nye.progtech.wumplus.service.util;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;

import java.util.ArrayList;
import java.util.List;

public class MapQuery {
    public static List<CoordinateVO> AllCoordinateOf(Character element, MapVO mapVO){
        List<CoordinateVO> result = new ArrayList<>();
        Integer rows = mapVO.getNumberOfRows();
        Integer cols = mapVO.getNumberOfColumns();
        char [][] map = mapVO.getMap();

        for (int i = 0; i < rows; i++) {
            char[] currentRow = map[i];
            for (int j = 0; j < cols; j++) {
                Character currentElement = currentRow[j];
                if (currentElement.equals(element)){
                    CoordinateVO new_coord = new CoordinateVO(i, j);
                    result.add(new_coord);
                }
            }
        }

        return result;
    }
}
