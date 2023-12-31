package hu.nye.progtech.wumplus.conduct;

import java.util.List;
import java.util.Optional;

import hu.nye.progtech.wumplus.conduct.menuperformer.OptionPerformer;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionSetPlayerName;
import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.MenuOptions;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import hu.nye.progtech.wumplus.ui.menu.LeaderBoardWriter;
import hu.nye.progtech.wumplus.ui.menu.MenuPrompt;
import hu.nye.progtech.wumplus.ui.menu.PlayerNamePrompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Conductor implementáció.
 */
public class CunductorImpl implements Conductor {

    private final Logger logger = LoggerFactory.getLogger(CunductorImpl.class);
    private Optional<GameState> gameState;
    private final MenuPrompt menuPrompt;
    private final PlayerNamePrompt playerNamePrompt;    
    private final List<OptionPerformer> optionPerformers;

    private final DatabaseService databaseService;

    private final LeaderBoardWriter leaderBoardWriter;

    public CunductorImpl(MenuPrompt menuPrompt, PlayerNamePrompt playerNamePrompt,
                         List<OptionPerformer> optionPerformers, DatabaseService dbService,
                         LeaderBoardWriter leaderBoardWriter) {
        this.menuPrompt = menuPrompt;
        this.playerNamePrompt = playerNamePrompt;
        this.optionPerformers = optionPerformers;
        this.gameState = Optional.empty();
        this.databaseService = dbService;
        this.leaderBoardWriter = leaderBoardWriter;
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

                    if (gameState.get().isPlayerWon()) {
                        try {
                            List<List<String>> highScores = databaseService.loadHighScore();
                            leaderBoardWriter.printHighScores(highScores);
                        } catch (Exception e) {
                            logger.error("Error in loading high scores : " + e.getMessage());
                        }
                    }
                } else {
                    logger.error("There is no game data. Exit.");
                    choice = MenuOptions.EXIT;
                }
            } else {
                logger.error("Wrong option selected.");
            }
        } while (!choice.equals(MenuOptions.EXIT));
    }

    boolean isCorrectOption(Integer choice) {
        return choice >= 1 && choice <= optionPerformers.size();
    }
}
