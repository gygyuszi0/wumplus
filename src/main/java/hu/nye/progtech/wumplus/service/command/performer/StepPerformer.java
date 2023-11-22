package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.exception.PlayerDeadException;
import hu.nye.progtech.wumplus.service.util.MapQuery;

/**
 * Lépés parancs végrehajtása.
 */
public class StepPerformer {

    /**
     * Végrehajtja a lépést.
     *
     * @param playerVO Melyik játékos lép?
     *
     * @param mapVO melyik map-en?
     *
     * @return Játékos az új koordinátával, ha lehet lépni.
     *
     * @throws MapQueryException Ha map-en kívülre szeretne lépni.
     *
     * @throws PerformerException Ha falra szeretne lépni.
     *
     */
    public PlayerVO perform(PlayerVO playerVO, MapVO mapVO) throws MapQueryException, PerformerException, PlayerDeadException {
        CoordinateVO frontOfCoordinate = MapQuery.getCoordFrontOfThePlayer(playerVO, mapVO);
        Character frontOfElement = MapQuery.getFieldByCoordinate(frontOfCoordinate, mapVO);

        if (frontOfElement.equals(Element.WALL)) {
            throw new PerformerException("You can't step to the wall.");
        } else if (frontOfElement.equals(Element.WUMP)) {
            throw new PlayerDeadException("Step to Wumpus, player is dead.");
        } else {
            PlayerVO result = new PlayerVO(playerVO.getName(), playerVO.getDirection(), frontOfCoordinate);
            result.setNonStatic(playerVO.getNumberOfArrows(), playerVO.getHaveGold(), playerVO.getScore(), playerVO.getNumberOfSteps() + 1);

            if (frontOfElement.equals(Element.PIT)) {
                Integer newArrow = Math.max(playerVO.getNumberOfArrows() - 1, 0);
                result.setNumberOfArrows(newArrow);
            }

            return result;
        }
    }
}
