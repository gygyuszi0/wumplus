package hu.nye.progtech.wumplus;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import hu.nye.progtech.wumplus.conduct.Conductor;
import hu.nye.progtech.wumplus.conduct.CunductorImpl;
import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import hu.nye.progtech.wumplus.service.persister.map.MapParser;
import hu.nye.progtech.wumplus.service.persister.player.PlayerParser;
import hu.nye.progtech.wumplus.service.util.IOService;
import hu.nye.progtech.wumplus.ui.MenuPerformer.*;
import hu.nye.progtech.wumplus.ui.MenuPrompt;
import hu.nye.progtech.wumplus.ui.PlayerNamePrompt;
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
        List<OptionPerformer> optionPerformers = Arrays.asList(
                new OptionCreateNewMap(),
                new OptionReadFromFile(),
                new OptionLoadDatabase(databaseService),
                new OptionSaveToDatabase(databaseService),
                new OptionPlay(),
                new OptionExit()
            );


        Conductor conductor = new CunductorImpl(menuPrompt, playerNamePrompt, optionPerformers);
        conductor.mainLoop();
    }
}