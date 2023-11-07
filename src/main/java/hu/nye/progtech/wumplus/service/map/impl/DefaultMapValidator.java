package hu.nye.progtech.wumplus.service.map.impl;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.service.map.MapValidator;

public class DefaultMapValidator implements MapValidator {

    @Override
    public Boolean validateMap(MapVO mapVO) {
        return null;
    }

    private Boolean dimensionsCorrect(MapVO mapVO){
        Integer dimensionX = mapVO.getNumberOfColumns();
        Integer dimensionY = mapVO.getNumberOfRows();

        Integer mapX = mapVO.getMap().length;
        Integer mapfixedX = mapVO.getFixed().length;
        return null;

    }
}
