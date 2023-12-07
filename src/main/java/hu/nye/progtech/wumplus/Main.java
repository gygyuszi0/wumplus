package hu.nye.progtech.wumplus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.nye.progtech.wumplus.conduct.Conductor;
import hu.nye.progtech.wumplus.conduct.CunductorImpl;
import hu.nye.progtech.wumplus.conduct.gamecontroller.ControllerImpl;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionCreateNewMap;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionExit;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionLoadDatabase;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionPerformer;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionPlay;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionReadFromFile;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionSaveToDatabase;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.service.command.impl.GiveUpCommand;
import hu.nye.progtech.wumplus.service.command.impl.LootCommand;
import hu.nye.progtech.wumplus.service.command.impl.PauseCommand;
import hu.nye.progtech.wumplus.service.command.impl.StepCommand;
import hu.nye.progtech.wumplus.service.command.impl.TurnCommand;
import hu.nye.progtech.wumplus.service.command.performer.LootPerformer;
import hu.nye.progtech.wumplus.service.command.performer.StepPerformer;
import hu.nye.progtech.wumplus.service.command.performer.TurnPerformer;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import hu.nye.progtech.wumplus.service.persister.json.JsonService;
import hu.nye.progtech.wumplus.service.persister.map.impl.BufferedReaderMapReader;
import hu.nye.progtech.wumplus.service.util.IOService;
import hu.nye.progtech.wumplus.ui.game.CommandPrompt;
import hu.nye.progtech.wumplus.ui.game.HudWriter;
import hu.nye.progtech.wumplus.ui.game.MapWriter;
import hu.nye.progtech.wumplus.ui.menu.LeaderBoardWriter;
import hu.nye.progtech.wumplus.ui.menu.MenuPrompt;
import hu.nye.progtech.wumplus.ui.menu.PlayerNamePrompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Wumplus Main function.
 */
public class Main {
    /**
     * Wumplus-game main függvény.
     *
     * @param args
     *
     */

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Wumplus-game main függvény.
     *
     * @param args Command line arguments
     *
     * @throws IOException read write error
     * @throws DBServiceException database error
     * @throws URISyntaxException wrong uri
     */
    public static void main(String[] args) throws IOException, DBServiceException, URISyntaxException {
        IOService ioService = new IOService();

        MenuPrompt menuPrompt = new MenuPrompt(ioService);
        PlayerNamePrompt playerNamePrompt = new PlayerNamePrompt(ioService);
        MapWriter mapWriter = new MapWriter(ioService);
        HudWriter hudWriter  = new HudWriter(ioService);
        LeaderBoardWriter leaderBoardWriter = new LeaderBoardWriter(ioService);
        CommandPrompt commandPrompt = new CommandPrompt(ioService);

        String resourceDir = "./";
        try {
            resourceDir = Main.class.getClassLoader().getSystemResource("").toURI().getPath();
            resourceDir = resourceDir.replace("\\", "/");
            logger.info("Resource directory: {}", resourceDir);
        } catch (Exception e) {
            logger.error("Error while getting resource directory");
        }
        DatabaseService databaseService = new DatabaseService(resourceDir);
        ObjectMapper  objectMapper = new ObjectMapper();
        JsonService jsonService = new JsonService(objectMapper, ioService, resourceDir);

        String inputFile = Main.class.getClassLoader().getResource("").getPath() + "wumpluszinput.txt";;
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedReaderMapReader mapReader = new BufferedReaderMapReader(bufferedReader);

        LootPerformer lootPerformer = new LootPerformer();
        StepPerformer  stepPerformer = new StepPerformer();
        TurnPerformer  turnPerformer = new TurnPerformer();
        List<Command> commands = Arrays.asList(
                new LootCommand(lootPerformer),
                new StepCommand(stepPerformer),
                new TurnCommand(turnPerformer),
                new GiveUpCommand(),
                new PauseCommand()
        );
        ControllerImpl gameController = new ControllerImpl(mapWriter, hudWriter, commandPrompt, commands);
        List<OptionPerformer> optionPerformers = Arrays.asList(
                new OptionCreateNewMap(),
                new OptionReadFromFile(mapReader),
                new OptionLoadDatabase(databaseService, jsonService),
                new OptionSaveToDatabase(databaseService, jsonService),
                new OptionPlay(gameController),
                new OptionExit()
            );


        Conductor conductor = new CunductorImpl(menuPrompt, playerNamePrompt,
                optionPerformers, databaseService, leaderBoardWriter);
        conductor.mainLoop();
    }
}