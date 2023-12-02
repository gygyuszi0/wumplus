package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.util.MapQuery;

/**
 * Loot parancsot végrehajtja.
 */
public class LootPerformer {

    /**
     * Loot parancsot végrehajtja.
     *
     * @param playerVO melyik játékos lootol?
     * @param mapVO melyik mapen
     *
     * @return A loot utáni játékos és a map.
     *
     * @throws MapQueryException ha nincs ilyen koordináta.
     * @throws PerformerException ha nincs gold a játékos előtti koordinátában.
     */
    public PlayerWithMap perform(PlayerVO playerVO, MapVO mapVO) throws MapQueryException, PerformerException {
        CoordinateVO frontOfCoordinate = MapQuery.getCoordFrontOfThePlayer(playerVO, mapVO);
        Character frontOfElement = MapQuery.getFieldByCoordinate(frontOfCoordinate, mapVO);
        
        if (frontOfElement.equals(Element.GOLD)) {

            MapVO newMapVO = MapQuery.setElementByCoordinate(mapVO, frontOfCoordinate, Element.SPACE);
            PlayerVO newPlayerVO = playerVO.deepCopy();
            newPlayerVO.setNonStatic(playerVO.getNumberOfArrows(), true, playerVO.getScore(), playerVO.getNumberOfSteps());


            return new PlayerWithMap(newPlayerVO, newMapVO);
        } else {
            throw new PerformerException("There are not gold front of the player.");
        }
    }
}
