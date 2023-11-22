package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;

import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.util.MapQuery;

public class LootPerformer {

    public MapVO perform(PlayerVO playerVO, MapVO mapVO) throws MapQueryException, PerformerException {
        CoordinateVO frontOfCoordinate = MapQuery.getCoordFrontOfThePlayer(playerVO, mapVO);
        Character frontOfElement = MapQuery.getFieldByCoordinate(frontOfCoordinate, mapVO);
        
        if (frontOfElement.equals(Element.GOLD)) {
            return MapQuery.setElementByCoordinate(mapVO, frontOfCoordinate, Element.SPACE);
        } else {
            throw new PerformerException("There are not gold front of the player.");
        }
    }
}
