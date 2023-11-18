package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.Command;

import java.util.regex.Pattern;

public class TurnCommand implements Command {

    private static final String TURN_PATTERN = String.format("^%s [%c,%c]",
            CommandConst.TURN,
            CommandConst.TURN_RIGHT,
            CommandConst.TURN_LEFT);

    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(TURN_PATTERN, input);
    }

    @Override
    public void process(String input) {

    }
}
