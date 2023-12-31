package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;

/**
 * Fordulás parancsot valósít meg.
 */
public class TurnPerformer {
    public TurnPerformer() {
    }

    /**
     * Elforgatja a játékost.
     *
     * @param existing Melyik játékost forgatja
     *
     * @param turnDirection Milyen irányban
     *
     * @return Elforgatott játékos
     *
     */
    public PlayerVO perform(PlayerVO existing, Character turnDirection) {
        Integer currentDirectionIndex = PlayerConst.CORRECT_DIRECTIONS.indexOf(existing.getDirection());

        Integer turnSignal = turnDirection.equals(CommandConst.TURN_RIGHT) ? 1 : -1;
        Integer newDirectionIndex = Math.floorMod(currentDirectionIndex + turnSignal, PlayerConst.CORRECT_DIRECTIONS.size());
        Character newDirection = PlayerConst.CORRECT_DIRECTIONS.get(newDirectionIndex);

        PlayerVO result = new PlayerVO(existing.getName(), newDirection, existing.getCoordinate(), existing.getStartCoordinate());
        result.setNonStatic(existing.getNumberOfArrows(), existing.getHaveGold(),
                existing.getScore(), existing.getNumberOfSteps());

        return result;
    }
}
