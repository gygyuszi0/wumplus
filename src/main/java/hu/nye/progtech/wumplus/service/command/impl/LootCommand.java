package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.service.command.performer.LootPerformer;

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

    }

    public GameState getGameState() {
        return gameState;
    }
}
