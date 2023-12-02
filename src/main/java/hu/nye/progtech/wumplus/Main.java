package hu.nye.progtech.wumplus;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import hu.nye.progtech.wumplus.conduct.Conductor;
import hu.nye.progtech.wumplus.conduct.CunductorImpl;
import hu.nye.progtech.wumplus.conduct.GameController.ControllerImpl;
import hu.nye.progtech.wumplus.conduct.MenuPerformer.*;
import hu.nye.progtech.wumplus.service.command.Command;
import hu.nye.progtech.wumplus.service.command.impl.GiveUpCommand;
import hu.nye.progtech.wumplus.service.command.impl.LootCommand;
import hu.nye.progtech.wumplus.service.command.impl.StepCommand;
import hu.nye.progtech.wumplus.service.command.impl.TurnCommand;
import hu.nye.progtech.wumplus.service.command.performer.LootPerformer;
import hu.nye.progtech.wumplus.service.command.performer.StepPerformer;
import hu.nye.progtech.wumplus.service.command.performer.TurnPerformer;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import hu.nye.progtech.wumplus.service.util.IOService;
import hu.nye.progtech.wumplus.ui.Game.CommandPrompt;
import hu.nye.progtech.wumplus.ui.Game.HudWriter;
import hu.nye.progtech.wumplus.ui.Game.MapWriter;
import hu.nye.progtech.wumplus.ui.Menu.MenuPrompt;
import hu.nye.progtech.wumplus.ui.Menu.PlayerNamePrompt;
//import hu.nye.progtech.wumplus.service.map.MapReadException;
//import hu.nye.progtech.wumplus.service.map.impl.DefaultMapParser;
//import hu.nye.progtech.wumplus.service.map.impl.MapFromFile;


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
    public static void main(String[] args) throws FileNotFoundException, DBServiceException {
        IOService ioService = new IOService();
        MenuPrompt menuPrompt = new MenuPrompt(ioService);
        PlayerNamePrompt playerNamePrompt = new PlayerNamePrompt(ioService);
        DatabaseService databaseService = new DatabaseService();

        MapWriter  mapWriter = new MapWriter(ioService);
        HudWriter hudWriter  = new HudWriter(ioService);
        CommandPrompt commandPrompt = new CommandPrompt(ioService);

        LootPerformer lootPerformer = new LootPerformer();
        StepPerformer  stepPerformer = new StepPerformer();
        TurnPerformer  turnPerformer = new TurnPerformer();
        List<Command> commands = Arrays.asList(
                new LootCommand(lootPerformer),
                new StepCommand(stepPerformer),
                new TurnCommand(turnPerformer),
                new GiveUpCommand()
        );
        ControllerImpl gameController = new ControllerImpl(mapWriter, hudWriter, commandPrompt, commands);

        List<OptionPerformer> optionPerformers = Arrays.asList(
                new OptionCreateNewMap(),
                new OptionReadFromFile(),
                new OptionLoadDatabase(databaseService),
                new OptionSaveToDatabase(databaseService),
                new OptionPlay(gameController),
                new OptionExit()
            );


        Conductor conductor = new CunductorImpl(menuPrompt, playerNamePrompt, optionPerformers);
        conductor.mainLoop();
    }
}