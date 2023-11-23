package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.service.command.performer.ShotPerformer;

public class ShotCommand implements Command {
    private final GameState gameState;
    private final ShotPerformer shotPerformer;

    public ShotCommand(GameState gameState, ShotPerformer shotPerformer) {
        this.gameState = gameState;
        this.shotPerformer = shotPerformer;
    }

    @Override
    public boolean canProcess(String input) {
        return input.equals(CommandConst.SHOT);
    }

    @Override
    public void process(String input) {

    }
}
