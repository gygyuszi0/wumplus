package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.service.command.performer.LootPerformer;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import org.slf4j.Logger;

import java.util.Optional;

/**
 * Loot parancsot.
 */
public class LootCommand implements Command {

//    private final GameState gameState;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(LootCommand.class);
    private final LootPerformer lootPerformer;

    public LootCommand(LootPerformer lootPerformer) {
        this.lootPerformer = lootPerformer;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(CommandConst.LOOT);
    }

    @Override
    public Optional<GameState> process(String input, Optional<GameState> safeGgameState) {
        try {
            if (safeGgameState.isPresent()) {
                GameState gameState = safeGgameState.get();
                PlayerWithMap performed = lootPerformer.perform(gameState.getPlayerVO(), gameState.getMapVO());

                PlayerVO newPlayerVo = performed.getPlayerVO();
                MapVO newMapVO = performed.getMapVO();
                gameState.setPlayerVO(newPlayerVo);
                gameState.setMapVO(newMapVO);

                return Optional.of(gameState);
            } else {
                logger.error("Game state is not present.");
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Error in game : " + e.getMessage());
            return safeGgameState;
        }
    }
}
