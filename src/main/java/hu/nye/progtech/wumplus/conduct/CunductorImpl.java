package hu.nye.progtech.wumplus.conduct;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.MenuOptions;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.util.IOService;
import hu.nye.progtech.wumplus.ui.MenuPerformer.OptionPerformer;
import hu.nye.progtech.wumplus.ui.MenuPerformer.OptionSetPlayerName;
import hu.nye.progtech.wumplus.ui.MenuPrompt;
import hu.nye.progtech.wumplus.ui.PlayerNamePrompt;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Conductor implementáció.
 */
public class CunductorImpl implements Conductor {

    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CunductorImpl.class);
    private String playerName;

    private Optional<GameState> gameState;

    private final MenuPrompt menuPrompt;
    private final PlayerNamePrompt playerNamePrompt;    
    private final IOService ioService;

    private final List<OptionPerformer> optionPerformers;

    public CunductorImpl(MenuPrompt menuPrompt, PlayerNamePrompt playerNamePrompt, IOService ioService, List<OptionPerformer> optionPerformers) {
        this.menuPrompt = menuPrompt;
        this.playerNamePrompt = playerNamePrompt;
        this.ioService = ioService;
        this.optionPerformers = optionPerformers;
        this.gameState = Optional.empty();
    }

    @Override
    public void mainLoop() {
        String player = playerNamePrompt.getPlayerName();
        OptionSetPlayerName optionSetPlayerName = new OptionSetPlayerName(player);
        gameState = optionSetPlayerName.perform(gameState);

        Integer choice;
        do {
            choice = menuPrompt.readChoice();
            if (gameState.isPresent()) {
                gameState = optionPerformers.get(choice - 1).perform(gameState);
            } else {
                LOGGER.error("There is no game data. Exit.");
                choice = MenuOptions.EXIT;
            }
        } while (!choice.equals(MenuOptions.EXIT));
    }
}
