package hu.nye.progtech.wumplus.service.map.impl;

import java.util.List;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.Element;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.service.exception.MapValidationException;
import hu.nye.progtech.wumplus.service.map.MapValidator;
import hu.nye.progtech.wumplus.service.util.Converter;
import hu.nye.progtech.wumplus.service.util.MapQuery;

/**
 * Összeveti a játékos helyzetét a map-pel.
 */
public class PlayerMapValidator implements MapValidator {

    private final PlayerVO playerVO;

    public PlayerMapValidator(PlayerVO playerVO) {
        this.playerVO = playerVO;
    }

    @Override
    public Boolean validateMap(MapVO mapVO) throws MapValidationException {
        intersectWall(mapVO);
        intersectPit(mapVO);
        intersectWumpus(mapVO);

        return true;
    }

    private void intersectWall(MapVO mapVO) throws MapValidationException {
        List<CoordinateVO> wallCoordinates = MapQuery.allCoordinateOf(Element.WALL, mapVO);

        CoordinateVO playerCoordinate = playerVO.getCoordinate();

        if (wallCoordinates.contains(playerCoordinate)) {
            throw new MapValidationException("Player on wall");
        }
    }

    private void intersectPit(MapVO mapVO) throws MapValidationException {
        List<CoordinateVO> pitCoordinates = MapQuery.allCoordinateOf(Element.PIT, mapVO);

        CoordinateVO playerCoordinate = playerVO.getCoordinate();

        if (pitCoordinates.contains(playerCoordinate)) {
            throw new MapValidationException("Player on pit");
        }

    }

    private void intersectWumpus(MapVO mapVO) throws MapValidationException {
        List<CoordinateVO> pitCoordinates = MapQuery.allCoordinateOf(Element.WUMP, mapVO);

        CoordinateVO playerCoordinate = playerVO.getCoordinate();

        if (pitCoordinates.contains(playerCoordinate)) {
            throw new MapValidationException("Player on Wumpus");
        }

    }

}

