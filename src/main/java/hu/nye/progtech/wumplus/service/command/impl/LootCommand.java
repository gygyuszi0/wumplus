package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.service.command.performer.LootPerformer;
import hu.nye.progtech.wumplus.service.exception.PerformerException;

public class LootCommand implements Command {

    private final GameState gameState;

    private final LootPerformer lootPerformer;

    public LootCommand(GameState gameState, LootPerformer lootPerformer) {
        this.gameState = gameState;
        this.lootPerformer = lootPerformer;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(CommandConst.LOOT);
    }

    @Override
    public void process(String input) {
        try {
            MapVO newMapVO =  lootPerformer.perform(gameState.getPlayerVO(), gameState.getMapVO());

            PlayerVO newPlayerVo = gameState.getPlayerVO();
            newPlayerVo.setHaveGold(true);
            gameState.setPlayerVO(newPlayerVo);
            gameState.setMapVO(newMapVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}
