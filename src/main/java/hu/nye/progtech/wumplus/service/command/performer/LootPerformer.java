package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;

import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.util.MapQuery;

public class LootPerformer {

    public PlayerWithMap perform(PlayerVO playerVO, MapVO mapVO) throws MapQueryException, PerformerException {
        CoordinateVO frontOfCoordinate = MapQuery.getCoordFrontOfThePlayer(playerVO, mapVO);
        Character frontOfElement = MapQuery.getFieldByCoordinate(frontOfCoordinate, mapVO);
        
        if (frontOfElement.equals(Element.GOLD)) {

            MapVO newMapVO = MapQuery.setElementByCoordinate(mapVO, frontOfCoordinate, Element.SPACE);
            PlayerVO newPlayerVO = new PlayerVO(playerVO.getName(), playerVO.getDirection(), playerVO.getCoordinate());
            newPlayerVO.setNonStatic(playerVO.getNumberOfArrows(), true, playerVO.getScore(), playerVO.getNumberOfSteps());


            return new PlayerWithMap(newPlayerVO, newMapVO);
        } else {
            throw new PerformerException("There are not gold front of the player.");
        }
    }
}
