package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;

public class TurnPerformer {
    private TurnPerformer() {
    }

    public static PlayerVO perform (PlayerVO existing, Character turnDirection){
        Integer currentDirectionIndex = PlayerConst.CORRECT_DIRECTIONS.indexOf(existing.getDirection());

        Integer turnSignal = turnDirection.equals(CommandConst.TURN_RIGHT) ? 1 : -1;
        Integer newDirectionIndex = Math.floorMod(currentDirectionIndex + turnSignal, PlayerConst.CORRECT_DIRECTIONS.size());
        Character new_direction = PlayerConst.CORRECT_DIRECTIONS.get(newDirectionIndex);

        PlayerVO result = new PlayerVO(existing.getName(), new_direction, existing.getCoordinate());
        result.setNonStatic(existing.getNumberOfArrows(), existing.getHaveGold(),
                existing.getScore(), existing.getNumberOfSteps());

        return result;
    }
}
