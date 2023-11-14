package hu.nye.progtech.wumplus.service.map.impl;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.Element;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.service.exception.MapValidationException;
import hu.nye.progtech.wumplus.service.map.MapValidator;
import hu.nye.progtech.wumplus.service.util.Converter;
import hu.nye.progtech.wumplus.service.util.MapQuery;

import java.util.List;

public class PlayerMapValidator implements MapValidator {

    private final PlayerVO playerVO;

    public PlayerMapValidator(PlayerVO playerVO) {
        this.playerVO = playerVO;
    }

    @Override
    public Boolean validateMap(MapVO mapVO) throws MapValidationException {
        IntersectWall(mapVO);

        return true;
    }

    private void IntersectWall(MapVO mapVO) throws MapValidationException {
        List<CoordinateVO> wallCoordinates = MapQuery.AllCoordinateOf(Element.WALL, mapVO);

        Integer playerX = Converter.LetterToInteger(playerVO.getCoordinateCols());
        Integer playerY = playerVO.getCoordinateRows();
        CoordinateVO playerCoordinate = new CoordinateVO(playerX - 1, playerY - 1);

        if (wallCoordinates.contains(playerCoordinate)){
            throw new MapValidationException("Player on wall");
        }
    }
}
