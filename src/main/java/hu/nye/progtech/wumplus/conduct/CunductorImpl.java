package hu.nye.progtech.wumplus.conduct;

import hu.nye.progtech.wumplus.model.constants.MenuOptions;
import hu.nye.progtech.wumplus.service.util.IOService;
import hu.nye.progtech.wumplus.ui.MenuPrompt;
import hu.nye.progtech.wumplus.ui.PlayerNamePrompt;

/**
 * Conductor implementáció.
 */
public class CunductorImpl implements Conductor {

    private String playerName;

    private final MenuPrompt menuPrompt;
    private final PlayerNamePrompt playerNamePrompt;    
    private final IOService ioService;  

    public CunductorImpl(MenuPrompt menuPrompt, PlayerNamePrompt playerNamePrompt, IOService ioService) {   
        this.menuPrompt = menuPrompt;
        this.playerNamePrompt = playerNamePrompt;
        this.ioService = ioService;
    }   

    @Override
    public void mainLoop() {
        this.playerName = playerNamePrompt.getPlayerName();
        Integer choice;
        do {
            choice = menuPrompt.readChoice();
        } while (!choice.equals(MenuOptions.EXIT));
    }
}
