package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;


/**
 * Option to set the player name.
 */
public class OptionSetPlayerName implements OptionPerformer {

    private final String playerName;

    public OptionSetPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public Optional<GameState> perform(Optional<GameState> gameState) {
        PlayerVO player = new PlayerVO(playerName, PlayerConst.NORTH, new CoordinateVO(0, 0), new CoordinateVO(0, 0));
        MapVO map = new MapVO(0, 0, new char[1][0], new boolean[1][0]);
        return Optional.of(new GameState(map, player, false, false));
    }
}
