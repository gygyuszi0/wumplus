package hu.nye.progtech.wumplus.service.map.impl;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.service.exception.MapValidationException;
import hu.nye.progtech.wumplus.service.map.MapValidator;
import hu.nye.progtech.wumplus.model.Element;

public class DefaultMapValidator implements MapValidator {

    
    @Override
    public Boolean validateMap(MapVO mapVO) throws MapValidationException {
        numberOfWumpus(mapVO);

        return true;
    }

    private void numberOfWumpus(MapVO mapVO) throws MapValidationException {
        Integer wumpusCount = 0;
        Integer rowNumber = mapVO.getNumberOfRows();
        Integer colNumber = mapVO.getNumberOfColumns();
        char[][] map = mapVO.getMap();

        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                if (map[i][j] == Element.WUMP){
                    wumpusCount++;
                }
            }
        }

        if ((rowNumber <= 8) && (wumpusCount != 1)){
            throw new MapValidationException("Wrong number of Wumpus. Excpected 1, map <= 8");
        }
        if (((rowNumber >= 9) && (rowNumber <= 14)) && (wumpusCount != 2)){
            throw new MapValidationException("Wrong number of Wumpus. Excpected 2, map in [9,14]");
        }
        if ((rowNumber > 14)&& (wumpusCount != 3)){
            throw new MapValidationException("Wrong number of Wumpus. Excpected 3, map > 14");
        }
    }

}
