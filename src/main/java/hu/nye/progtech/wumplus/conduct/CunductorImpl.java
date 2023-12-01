package hu.nye.progtech.wumplus.conduct;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.MenuOptions;
import hu.nye.progtech.wumplus.conduct.MenuPerformer.OptionPerformer;
import hu.nye.progtech.wumplus.conduct.MenuPerformer.OptionSetPlayerName;
import hu.nye.progtech.wumplus.ui.Menu.MenuPrompt;
import hu.nye.progtech.wumplus.ui.Menu.PlayerNamePrompt;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Conductor implementáció.
 */
public class CunductorImpl implements Conductor {

    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CunductorImpl.class);
    private Optional<GameState> gameState;
    private final MenuPrompt menuPrompt;
    private final PlayerNamePrompt playerNamePrompt;    
    private final List<OptionPerformer> optionPerformers;

    public CunductorImpl(MenuPrompt menuPrompt, PlayerNamePrompt playerNamePrompt, List<OptionPerformer> optionPerformers) {
        this.menuPrompt = menuPrompt;
        this.playerNamePrompt = playerNamePrompt;
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
            if (isCorrectOption(choice)) {
                if (gameState.isPresent()) {
                    gameState = optionPerformers.get(choice - 1).perform(gameState);
                } else {
                    LOGGER.error("There is no game data. Exit.");
                    choice = MenuOptions.EXIT;
                }
            } else {
                LOGGER.error("Wrong option selected.");
            }
        } while (!choice.equals(MenuOptions.EXIT));
    }

    boolean isCorrectOption(Integer choice) {
        return choice >= 1 && choice <= optionPerformers.size();
    }
}
