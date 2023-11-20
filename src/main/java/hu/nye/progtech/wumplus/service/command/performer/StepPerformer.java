package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.util.MapQuery;

public class StepPerformer {

    public PlayerVO perform(PlayerVO playerVO, MapVO mapVO) throws MapQueryException, PerformerException {
        CoordinateVO frontOfCoordinate = MapQuery.getCoordFrontOfThePlayer(playerVO, mapVO);
        Character frontOfElement = MapQuery.getFieldByCoordinate(frontOfCoordinate, mapVO);

        if (frontOfElement.equals(Element.WALL)) {
            throw new PerformerException("You can't step to the wall.");
        }
        else {
            PlayerVO result = new PlayerVO(playerVO.getName(), playerVO.getDirection(), frontOfCoordinate);
            result.setNonStatic(playerVO.getNumberOfArrows(), playerVO.getHaveGold(), playerVO.getScore(), playerVO.getNumberOfSteps() + 1);
            return result;
        }
    }
}
