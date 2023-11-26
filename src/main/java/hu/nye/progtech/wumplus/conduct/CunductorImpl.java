package hu.nye.progtech.wumplus.conduct;

import hu.nye.progtech.wumplus.model.constants.MenuOptions;
import hu.nye.progtech.wumplus.ui.MenuPrompt;
import hu.nye.progtech.wumplus.ui.PlayerNamePrompt;
public class CunductorImpl implements Conductor{

    private String playerName;
    public CunductorImpl() {
    }

    @Override
    public void mainLoop() {
        this.playerName = PlayerNamePrompt.getPlayerName();
        Integer choice;
        do {
            choice = MenuPrompt.readChoice();
        } while (!choice.equals(MenuOptions.EXIT));
    }
}
